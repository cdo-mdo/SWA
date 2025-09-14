package com.example.transfer.web;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record TransferRequest(@NotNull Long fromCheckingId,
                              @NotNull Long toSavingId,
                              @DecimalMin("1") BigDecimal amount,
                              boolean simulateSavingError) {}

public record TransferResponse(String transferId, String state) {}
