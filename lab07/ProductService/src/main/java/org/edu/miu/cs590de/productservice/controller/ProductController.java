package org.edu.miu.cs590de.productservice.controller;

import org.edu.miu.cs590de.productservice.entity.Product;
import org.edu.miu.cs590de.productservice.service.ProductServiceRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductServiceRest productServiceRest;

    @GetMapping("")
    public Product getProduct(@RequestParam String productNumber) {
        return productServiceRest.getProductByProductNumber(productNumber);
    }
}
