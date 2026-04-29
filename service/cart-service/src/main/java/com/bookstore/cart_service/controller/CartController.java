package com.bookstore.cart_service.controller;

import com.bookstore.cart_service.dto.CartRequest;
import com.bookstore.cart_service.dto.CartResponse;
import com.bookstore.cart_service.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService service;

    @PostMapping("/add")
    public CartResponse addToCart(@RequestBody CartRequest request) {
        return service.addToCart(request);
    }

    @GetMapping("/{userId}")
    public List<CartResponse> getCart(@PathVariable Long userId) {
        return service.getUserCart(userId);
    }

    @DeleteMapping("/{id}")
    public String removeFromCart(@PathVariable Long id) {
        service.removeFromCart(id);
        return "Item removed from cart";
    }

    // Feign test
    @GetMapping("/products")
    public String getProducts() {
        return service.getProductsFromProductService();
    }
}