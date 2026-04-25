package com.bookstore.order_service.controller;

import com.bookstore.order_service.dto.request.OrderRequest;
import com.bookstore.order_service.dto.response.OrderResponse;
import com.bookstore.order_service.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService service;

    @PostMapping
    public OrderResponse placeOrder(@RequestBody OrderRequest request) {
        return service.placeOrder(request);
    }

    @GetMapping("/{id}")
    public OrderResponse getOrder(@PathVariable Long id) {
        return service.getOrderById(id);
    }

    @GetMapping("/test")
    public String test() {
        return "order-service working";
    }
}