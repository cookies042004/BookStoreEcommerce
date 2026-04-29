package com.bookstore.cart_service.service;

import com.bookstore.cart_service.dto.CartRequest;
import com.bookstore.cart_service.dto.CartResponse;
import com.bookstore.cart_service.entity.CartItem;
import com.bookstore.cart_service.exception.CartNotFoundException;
import com.bookstore.cart_service.feign.ProductClient;
import com.bookstore.cart_service.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository repository;
    private final ProductClient productClient;

    public CartResponse addToCart(CartRequest request) {

        CartItem item = new CartItem();
        item.setUserId(request.getUserId());
        item.setProductId(request.getProductId());
        item.setQuantity(request.getQuantity());

        CartItem saved = repository.save(item);

        return mapToResponse(saved);
    }

    public List<CartResponse> getUserCart(Long userId) {

        List<CartItem> items = repository.findByUserId(userId);

        if (items.isEmpty()) {
            throw new CartNotFoundException("Cart is empty for user " + userId);
        }

        return items.stream()
                .map(this::mapToResponse)
                .toList();
    }

    public void removeFromCart(Long id) {

        if (!repository.existsById(id)) {
            throw new CartNotFoundException("Cart item not found");
        }

        repository.deleteById(id);
    }

    public String getProductsFromProductService() {
        return productClient.getProducts();
    }

    private CartResponse mapToResponse(CartItem item) {
        return CartResponse.builder()
                .id(item.getId())
                .userId(item.getUserId())
                .productId(item.getProductId())
                .quantity(item.getQuantity())
                .build();
    }
}