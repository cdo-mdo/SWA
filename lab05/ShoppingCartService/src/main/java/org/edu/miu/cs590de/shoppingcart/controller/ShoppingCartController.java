package org.edu.miu.cs590de.shoppingcart.controller;

import org.edu.miu.cs590de.shoppingcart.entity.Product;
import org.edu.miu.cs590de.shoppingcart.entity.ShoppingCart;
import org.edu.miu.cs590de.shoppingcart.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shoppingcarts")
public class ShoppingCartController {
    @Autowired
    private ShoppingCartService shoppingCartService;

    @GetMapping("/{cartId}")
    public ResponseEntity<ShoppingCart> getCart(@PathVariable String cardId) {
        return ResponseEntity.ok(shoppingCartService.getCart(cardId));
    }

    @PostMapping("/{cartId}/add")
    public ResponseEntity<ShoppingCart> addProduct(
            @PathVariable String cartId,
            @RequestBody Product product) {
        return ResponseEntity.ok(shoppingCartService.addProduct(cartId, product));
    }

    @DeleteMapping("/{cartId}/remove/{productId}")
    public ResponseEntity<ShoppingCart> removeProduct(
            @PathVariable String cartId,
            @PathVariable String productId) {
        return ResponseEntity.ok(shoppingCartService.removeProduct(cartId, productId));
    }
}
