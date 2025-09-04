package cs590de.produccommandservice.repo;

import cs590de.produccommandservice.domain.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ProductRepository extends MongoRepository<Product, String> {
    Optional<Product> findByProductNumber(String productNumber);
    boolean existsByProductNumber(String productNumber);
    void deleteByProductNumber(String productNumber);
}
