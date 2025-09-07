package cs590de.productservice.controller;

import cs590de.productservice.entity.Product;
import cs590de.productservice.integration.StockClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final StockClient stockClient;

    public ProductController(StockClient stockClient) {
        this.stockClient = stockClient;
    }

    private String nameFor(String productNumber) {
        return switch (productNumber) {
            case "P100" -> "Laptop";
            case "P200" -> "Headphones";
            case "P300" -> "Keyboard";
            default -> "Unknown";
        };
    }

    @GetMapping("/{productNumber}")
    public Product get(@PathVariable String productNumber) {
        var stock = stockClient.getStock(productNumber);
        return new Product(
                productNumber,
                nameFor(productNumber),
                stock.quantity(),
                stock.servedBy()
        );
    }
}
