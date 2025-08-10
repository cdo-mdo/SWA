package org.edu.miu.cs590de.productservice.service;

import org.edu.miu.cs590de.productservice.entity.Product;
import org.edu.miu.cs590de.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private RestTemplate restTemplate;

    private static final String STOCK_SERVICE_URL = "http://localhost:8900/api/products/stock";

    public Product getProductByProductNumber(String productNumber) {
        // Fetch the product from product database using the product number
        Product product = productRepository.findByNumber(productNumber);

        // Retrieve the stock quantity from the stock service using RestTemplate
        int stockQuantity = getStockQuantityFromStockService(productNumber);

        product.setQuantity(stockQuantity);

        return product;
    }

    private int getStockQuantityFromStockService(String productNumber) {
        // Create the full URL for the stock service call
        String url = STOCK_SERVICE_URL + "?productNumber=" + productNumber;

        // Call the StockService and get the stock quantity (assuming the response is integer)
        Integer stockQuantity = restTemplate.getForObject(url, Integer.class);

        // Return the stock quantity (default to 0 if not found)
        return stockQuantity != null ? stockQuantity : 0;
    }
}
