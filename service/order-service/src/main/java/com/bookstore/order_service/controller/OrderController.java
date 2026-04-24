package com.bookstore.order_service.controller;

import com.bookstore.order_service.entity.Orders;
import com.bookstore.order_service.service.OrderService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @GetMapping("/test")
    public String test() {
        return "working";
    }

    @PostMapping
    public Orders placeOrder(@RequestBody Orders order) {
        return service.placeOrder(order);
    }
}