package com.bookstore.cart_service.service;

import com.bookstore.cart_service.entity.CartItem;
import com.bookstore.cart_service.repository.CartRepository;
import com.bookstore.cart_service.feign.ProductClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    private final CartRepository repo;

    // Feign Client to call Product Service
    private final ProductClient productClient;

    public CartService(CartRepository repo, ProductClient productClient) {
        this.repo = repo;
        this.productClient = productClient;
    }

    // Add item to cart (saves in DB)
    public CartItem addToCart(CartItem item) {
        return repo.save(item);
    }

    // Fetch all cart items for a specific user
    public List<CartItem> getUserCart(Long userId) {
        return repo.findByUserId(userId);
    }

    // Calls Product Service using Feign Client
    public String getProductsFromProductService() {
        return productClient.getProducts();
    }
}