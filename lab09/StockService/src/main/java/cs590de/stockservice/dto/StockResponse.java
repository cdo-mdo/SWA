package cs590de.stockservice.dto;

public record StockResponse (String productNumber, int quantity, String serverBy) {
}
