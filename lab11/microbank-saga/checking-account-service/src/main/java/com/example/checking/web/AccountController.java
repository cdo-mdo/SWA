package com.example.checking.web;

import com.example.checking.domain.Account;
import com.example.checking.repo.AccountRepository;
import com.example.checking.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService service;
    private final AccountRepository accountRepository;

    @PostMapping
    public ResponseEntity<Account> create(@RequestBody @Valid CreateAccountRequest req) {
        return ResponseEntity.ok(service.create(req.owner(), req.initial()));
    }

    @PostMapping("/deposit")
    public ResponseEntity<Account> deposit(@RequestBody @Valid MoneyRequest req) {
        return ResponseEntity.ok(service.deposit(req.transferId(), req.accountId(), req.amount()));
    }

    @PostMapping("/withdraw")
    public ResponseEntity<Account> withdraw(@RequestBody @Valid MoneyRequest req) {
        return ResponseEntity.ok(service.withdraw(req.transferId(), req.accountId(), req.amount()));
    }

    @PostMapping("/compensate")
    public ResponseEntity<Void> compensate(@RequestBody @Valid CompensateRequest req) {
        service.compensateWithdraw(req.transferId());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Account>> get(@PathVariable Long id) {
        return ResponseEntity.ok(accountRepository.findById(id));
    }
}
