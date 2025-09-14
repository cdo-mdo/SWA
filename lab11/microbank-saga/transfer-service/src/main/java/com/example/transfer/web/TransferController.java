package com.example.transfer.web;

import com.example.transfer.domain.Transfer;
import com.example.transfer.repo.TransferRepository;
import com.example.transfer.service.TransferService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transfers")
@RequiredArgsConstructor
public class TransferController {

    private final TransferService service;
    private final TransferRepository repo;

    @PostMapping
    public ResponseEntity<TransferResponse> create(@RequestBody @Valid TransferRequest req) {
        Transfer t = service.startTransfer(req.fromCheckingId(), req.toSavingId(), req.amount(), req.simulateSavingError());
        return ResponseEntity.ok(new TransferResponse(t.getId(), t.getState().name()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transfer> get(@PathVariable String id) {
        return ResponseEntity.of(repo.findById(id));
    }
}
