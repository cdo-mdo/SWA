package org.edu.miu.cs590de.product.controller;

import org.edu.miu.cs590de.product.entity.Product;
import org.edu.miu.cs590de.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    @GetMapping
    public Product getProduct(@PathVariable String id) {
        return productService.getProductById(id).orElse(null);
    }

    @PutMapping
    public Product updateProduct(@PathVariable String id, @RequestBody Product product) {
        updateProduct(id, product);
        return productService.getProductById(id).orElse(null);
    }

    @DeleteMapping
    public void deleteProduct(@PathVariable String id) {
        productService.deleteProduct(id);
    }
}
