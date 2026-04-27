package com.bookstore.cart_service.controller;

import com.bookstore.cart_service.entity.CartItem;
import com.bookstore.cart_service.service.CartService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService service;

    public CartController(CartService service) {
        this.service = service;
    }

    @PostMapping("/add")
    public CartItem addToCart(@RequestBody CartItem item) {
        return service.addToCart(item);
    }

    @GetMapping("/{userId}")
    public List<CartItem> getCart(@PathVariable Long userId) {
        return service.getUserCart(userId);
    }

    @GetMapping("/products")
    public String getProducts() {
        return service.getProductsFromProductService();
    }
}