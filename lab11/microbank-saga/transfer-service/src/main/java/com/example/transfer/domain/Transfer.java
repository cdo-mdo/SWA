package com.example.transfer.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
@Table(name = "transfers")
public class Transfer {

    @Id
    private String id; // UUID

    private Long fromCheckingId;
    private Long toSavingId;
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private State state;

    private Instant createdAt;
    private Instant updatedAt;

    public enum State { INITIATED, WITHDRAWN, DEPOSITED, COMPENSATED, FAILED }

    @PrePersist
    public void onCreate() {
        createdAt = Instant.now();
        updatedAt = createdAt;
        if (id == null) id = UUID.randomUUID().toString();
    }

    @PreUpdate
    public void onUpdate() {
        updatedAt = Instant.now();
    }
}
