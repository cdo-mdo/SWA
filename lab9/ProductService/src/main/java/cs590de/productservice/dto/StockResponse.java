package cs590de.productservice.dto;

public record StockResponse(String productNumber, int quantity, String servedBy) {}
