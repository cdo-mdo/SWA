package com.example.saving.service;

import com.example.saving.domain.Account;
import com.example.saving.domain.LedgerEntry;
import com.example.saving.repo.AccountRepository;
import com.example.saving.repo.LedgerEntryRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepo;
    private final LedgerEntryRepository ledgerRepo;

    @Transactional
    public Account create(String owner, BigDecimal initial) {
        if (initial.compareTo(BigDecimal.ZERO) < 0) throw new IllegalArgumentException("Initial < 0");
        return accountRepo.save(Account.builder().owner(owner).balance(initial).build());
    }

    @Transactional
    public Account deposit(String transferId, Long accountId, BigDecimal amount, boolean simulateError) {
        if (amount.signum() <= 0) throw new IllegalArgumentException("amount <= 0");
        // simulate a failure to test saga compensation
        if (simulateError) throw new IllegalStateException("Simulated failure in SavingAccountService.deposit");

        // idempotent
        if (ledgerRepo.findByTransferIdAndType(transferId, LedgerEntry.Type.DEPOSIT).isPresent()) {
            return accountRepo.findById(accountId).orElseThrow();
        }
        Account acc = accountRepo.findById(accountId).orElseThrow();
        acc.setBalance(acc.getBalance().add(amount));
        Account saved = accountRepo.save(acc);
        ledgerRepo.save(LedgerEntry.builder()
                .transferId(transferId)
                .accountId(accountId)
                .amount(amount)
                .type(LedgerEntry.Type.DEPOSIT)
                .createdAt(Instant.now())
                .build());
        return saved;
    }
}
