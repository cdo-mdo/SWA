package com.example.saving.web;

import com.example.saving.domain.Account;
import com.example.saving.repo.AccountRepository;
import com.example.saving.service.AccountService;
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
        return ResponseEntity.ok(service.deposit(req.transferId(), req.accountId(), req.amount(), req.simulateError()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Account>> get(@PathVariable Long id) {
        return ResponseEntity.ok(accountRepository.findById(id));
    }
}
