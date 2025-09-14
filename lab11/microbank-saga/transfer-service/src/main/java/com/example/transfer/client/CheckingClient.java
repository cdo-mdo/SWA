package com.example.transfer.client;

import lombok.Builder;

import java.math.BigDecimal;

public class CheckingClient {

    @Builder
    public record MoneyRequest(String transferId, Long accountId, BigDecimal amount) {}

}
