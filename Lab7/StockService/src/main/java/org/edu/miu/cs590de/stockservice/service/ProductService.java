package org.edu.miu.cs590de.stockservice.service;

import org.edu.miu.cs590de.stockservice.entity.Product;
import org.edu.miu.cs590de.stockservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public int getProductStock(String productNumber) {
        Optional<Product> product = productRepository.findByNumber(productNumber);
        return product.map(Product::getQuantity).orElse(0);
    }
}
