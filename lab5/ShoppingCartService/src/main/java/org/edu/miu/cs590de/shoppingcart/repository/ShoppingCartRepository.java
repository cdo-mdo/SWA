package org.edu.miu.cs590de.shoppingcart.repository;

import org.edu.miu.cs590de.shoppingcart.entity.ShoppingCart;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartRepository extends MongoRepository<ShoppingCart, String> {
}
