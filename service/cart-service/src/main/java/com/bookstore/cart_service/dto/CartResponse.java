package com.bookstore.cart_service.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CartResponse {
    private Long id;
    private Long userId;
    private Long productId;
    private int quantity;
}