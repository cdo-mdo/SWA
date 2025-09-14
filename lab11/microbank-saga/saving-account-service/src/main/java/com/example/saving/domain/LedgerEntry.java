package com.example.saving.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
@Table(name = "ledger_entries", indexes = {
        @Index(name="idx_transferId_type", columnList = "transferId,type", unique = true)
})
public class LedgerEntry {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String transferId;

    private Long accountId;

    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private Type type; // WITHDRAW, DEPOSIT, COMPENSATE

    private Instant createdAt;

    public enum Type { WITHDRAW, DEPOSIT, COMPENSATE }
}
