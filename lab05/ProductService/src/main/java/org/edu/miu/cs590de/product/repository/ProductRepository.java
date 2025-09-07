package org.edu.miu.cs590de.product.repository;


import org.edu.miu.cs590de.product.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}
