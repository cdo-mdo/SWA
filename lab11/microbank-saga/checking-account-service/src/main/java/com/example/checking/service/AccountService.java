package com.example.checking.service;

import com.example.checking.domain.Account;
import com.example.checking.domain.LedgerEntry;
import com.example.checking.repo.AccountRepository;
import com.example.checking.repo.LedgerEntryRepository;
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
    public Account deposit(String transferId, Long accountId, BigDecimal amount) {
        if (amount.signum() <= 0) throw new IllegalArgumentException("amount <= 0");
        // idempotent: if deposit already recorded for this transfer, return current account
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

    @Transactional
    public Account withdraw(String transferId, Long accountId, BigDecimal amount) {
        if (amount.signum() <= 0) throw new IllegalArgumentException("amount <= 0");
        // idempotent: if withdraw already recorded for this transfer, return current account
        if (ledgerRepo.findByTransferIdAndType(transferId, LedgerEntry.Type.WITHDRAW).isPresent()) {
            return accountRepo.findById(accountId).orElseThrow();
        }
        Account acc = accountRepo.findById(accountId).orElseThrow();
        if (acc.getBalance().compareTo(amount) < 0) throw new IllegalStateException("Insufficient funds");
        acc.setBalance(acc.getBalance().subtract(amount));
        Account saved = accountRepo.save(acc);
        ledgerRepo.save(LedgerEntry.builder()
                .transferId(transferId)
                .accountId(accountId)
                .amount(amount)
                .type(LedgerEntry.Type.WITHDRAW)
                .createdAt(Instant.now())
                .build());
        return saved;
    }

    @Transactional
    public void compensateWithdraw(String transferId) {
        var withdraw = ledgerRepo.findByTransferIdAndType(transferId, LedgerEntry.Type.WITHDRAW);
        if (withdraw.isEmpty()) return; // nothing to compensate
        var compExists = ledgerRepo.findByTransferIdAndType(transferId, LedgerEntry.Type.COMPENSATE);
        if (compExists.isPresent()) return; // already compensated
        var entry = withdraw.get();
        Account acc = accountRepo.findById(entry.getAccountId()).orElseThrow();
        acc.setBalance(acc.getBalance().add(entry.getAmount())); // refund
        accountRepo.save(acc);
        ledgerRepo.save(LedgerEntry.builder()
                .transferId(transferId)
                .accountId(entry.getAccountId())
                .amount(entry.getAmount())
                .type(LedgerEntry.Type.COMPENSATE)
                .createdAt(Instant.now())
                .build());
    }
}
