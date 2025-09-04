package cs590de.produccommandservice.controller;


import cs590de.produccommandservice.domain.Product;
import cs590de.produccommandservice.dto.UpsertProductRequest;
import cs590de.produccommandservice.repo.ProductRepository;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductRepository repo;
    private final WebRequest webRequest;

    public ProductController(ProductRepository productRepository, WebRequest webRequest) {
        this.repo = productRepository;
        this.webRequest = webRequest;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product create(@RequestBody UpsertProductRequest req) {
        if (repo.existsByProductNumber(req.productNumber)) {
            throw new DuplicateKeyException("productNumber already exists");
        }
        return repo.save(new Product(req.productNumber, req.name, req.price));
    }

    @PutMapping("/{productNumber}")
    public Product update(@PathVariable String productNumber, @RequestBody UpsertProductRequest req) {
        var existing = repo.findByProductNumber(productNumber)
                .orElseThrow(() -> new NoSuchElementException("Product not found"));
        if (req.name != null) existing.setName(req.name);
        if (req.price != null) existing.setPrice(req.price);
        return repo.save(existing);
    }

    @DeleteMapping("/{productNumber}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String productNumber) {
        if (!repo.existsByProductNumber(productNumber)) {
            throw new NoSuchElementException("Product not found");
        }
        repo.deleteByProductNumber(productNumber);
    }
}
