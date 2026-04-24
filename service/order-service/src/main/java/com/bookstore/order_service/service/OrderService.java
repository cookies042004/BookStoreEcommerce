package com.bookstore.order_service.service;

import com.bookstore.order_service.entity.Orders;
import com.bookstore.order_service.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository repo;

    // Kafka producer to publish events
    private final OrderProducer producer;

    public OrderService(OrderRepository repo, OrderProducer producer) {
        this.repo = repo;
        this.producer = producer;
    }

    // Business method to place an order
    public Orders placeOrder(Orders order) {
        Orders saved = repo.save(order);

        // 2. Send event to Kafka (async communication)
        producer.sendOrderEvent("Order placed: " + saved.getId());

        return saved;
    }
}
