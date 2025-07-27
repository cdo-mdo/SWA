package org.edu.miu.cs590de.shoppingcart.service;

import org.edu.miu.cs590de.shoppingcart.entity.Product;
import org.edu.miu.cs590de.shoppingcart.entity.ProductItem;
import org.edu.miu.cs590de.shoppingcart.entity.ShoppingCart;
import org.edu.miu.cs590de.shoppingcart.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
public class ShoppingCartService {
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    public ShoppingCart getCart(String cartId) {
        return shoppingCartRepository.findById(cartId).
                orElseThrow(() -> new RuntimeException("Cart not found"));
    }

    public ShoppingCart addProduct(String cartId, Product product) {
        ShoppingCart cart = shoppingCartRepository.findById(cartId).orElse(new ShoppingCart(cartId, new ArrayList()));

        if (cart.getProductItems().isEmpty()) {
            ProductItem item = new ProductItem(UUID.randomUUID().toString(), new ArrayList<>());
        }

        cart.getProductItems().getFirst().getProducts().add(product);
        return shoppingCartRepository.save(cart);
    }

    public ShoppingCart removeProduct(String cartId, String productId) {
        ShoppingCart cart = shoppingCartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        for (ProductItem item : cart.getProductItems()) {
            item.getProducts().removeIf(p -> p.getId().equals(productId));
        }

        return shoppingCartRepository.save(cart);
    }

}
