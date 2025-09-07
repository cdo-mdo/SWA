package cs590de.productservice.controller;

import cs590de.productservice.entity.Product;
import cs590de.productservice.integration.StockClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products/")
public class ProductController {
    private final StockClient stockClient;

    public ProductController(StockClient stockClient) {
        this.stockClient = stockClient;
    }

    public Product get(@PathVariable String productNumber) {
        Integer quantity = stockClient.getStock(productNumber);
        return new Product(productNumber, "Demo Product #" + productNumber, quantity);
    }
}
