package com.example.saving.web;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CreateAccountRequest(@NotNull String owner, @DecimalMin("0") BigDecimal initial) {}
public record MoneyRequest(@NotNull String transferId, @NotNull Long accountId, @DecimalMin("1") BigDecimal amount, boolean simulateError) {}
