package org.edu.miu.cs590de.productservice.repository;

import org.edu.miu.cs590de.productservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    public Product findByNumber(String productNumber);
}
