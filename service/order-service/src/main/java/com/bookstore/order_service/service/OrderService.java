package com.bookstore.order_service.service;

import com.bookstore.order_service.dto.request.OrderRequest;
import com.bookstore.order_service.dto.response.OrderResponse;
import com.bookstore.order_service.entity.Orders;
import com.bookstore.order_service.exception.OrderNotFoundException;
import com.bookstore.order_service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository repo;
    private final OrderProducer producer;

    public OrderResponse placeOrder(OrderRequest request) {

        Orders order = new Orders();
        order.setUserId(request.getUserId());
        order.setProductId(request.getProductId());

        Orders saved = repo.save(order);

        producer.sendOrderEvent("Order placed: " + saved.getId());

        return OrderResponse.builder()
                .id(saved.getId())
                .userId(saved.getUserId())
                .productId(saved.getProductId())
                .build();
    }

    public OrderResponse getOrderById(Long id) {

        Orders order = repo.findById(id)
                .orElseThrow(() ->
                        new OrderNotFoundException("Order with id " + id + " not found")
                );

        return OrderResponse.builder()
                .id(order.getId())
                .userId(order.getUserId())
                .productId(order.getProductId())
                .build();
    }
}