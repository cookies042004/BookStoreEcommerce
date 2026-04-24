package com.bookstore.order_service.dto.request;

import lombok.Data;

@Data
public class OrderRequest {
    private Long userId;
    private Long productId;
}
