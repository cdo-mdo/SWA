package com.example.transfer.service;

import com.example.transfer.client.CheckingClient;
import com.example.transfer.client.SavingClient;
import com.example.transfer.domain.Transfer;
import com.example.transfer.repo.TransferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class TransferService {

    private final RestTemplate rest;
    private final TransferRepository repo;

    @Value("${client.checkingBaseUrl}")
    private String checkingBaseUrl;

    @Value("${client.savingBaseUrl}")
    private String savingBaseUrl;

    @Transactional
    public Transfer startTransfer(Long fromCheckingId, Long toSavingId, BigDecimal amount, boolean simulateSavingError) {
        var transfer = Transfer.builder()
                .fromCheckingId(fromCheckingId)
                .toSavingId(toSavingId)
                .amount(amount)
                .state(Transfer.State.INITIATED)
                .build();
        transfer = repo.save(transfer);

        String transferId = transfer.getId();
        try {
            // Step 1: withdraw from checking
            var withdrawReq = new CheckingClient.MoneyRequest(transferId, fromCheckingId, amount);
            postJson(checkingBaseUrl + "/accounts/withdraw", withdrawReq);
            transfer.setState(Transfer.State.WITHDRAWN);
            repo.save(transfer);

            // Step 2: deposit to saving (may throw for simulation)
            var depositReq = new SavingClient.MoneyRequest(transferId, toSavingId, amount, simulateSavingError);
            postJson(savingBaseUrl + "/accounts/deposit", depositReq);
            transfer.setState(Transfer.State.DEPOSITED);
            repo.save(transfer);
            return transfer;

        } catch (HttpStatusCodeException | RuntimeException e) {
            // compensation
            try {
                var compensateBody = new java.util.HashMap<String, Object>();
                compensateBody.put("transferId", transferId);
                postJson(checkingBaseUrl + "/accounts/compensate", compensateBody);
                transfer.setState(Transfer.State.COMPENSATED);
            } catch (Exception ce) {
                transfer.setState(Transfer.State.FAILED);
            }
            repo.save(transfer);
            throw e; // bubble up so client sees the failure
        }
    }

    private void postJson(String url, Object body) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> entity = new HttpEntity<>(body, headers);
        rest.postForEntity(url, entity, String.class);
    }
}
