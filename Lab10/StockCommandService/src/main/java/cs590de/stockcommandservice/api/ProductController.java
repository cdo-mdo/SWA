package cs590de.stockcommandservice.api;

import cs590de.stockcommandservice.domain.Product;
import cs590de.stockcommandservice.dto.UpsertProductRequest;
import cs590de.stockcommandservice.repository.ProductRepository;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductRepository repo;

    public ProductController(ProductRepository repo) {
        this.repo = repo;
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
