package com.bookstore.order_service.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderResponse {
    private Long id;
    private Long userId;
    private Long productId;
}
