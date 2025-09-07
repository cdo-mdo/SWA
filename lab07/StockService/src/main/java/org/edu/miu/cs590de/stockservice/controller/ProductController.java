package org.edu.miu.cs590de.stockservice.controller;

import org.edu.miu.cs590de.stockservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/stock")
    public int getSock(@RequestParam String productNumber) {
        return 100;
//        return productService.getProductStock(productNumber);
    }
}
