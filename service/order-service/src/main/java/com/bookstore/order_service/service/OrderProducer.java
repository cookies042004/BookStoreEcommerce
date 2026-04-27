package com.bookstore.order_service.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
public class OrderProducer {

    // KafkaTemplate is used to send messages to Kafka
    private final KafkaTemplate<String, String> kafkaTemplate;

    public OrderProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    // Method to send message/event to Kafka topic
    public void sendOrderEvent(String message) {

        // "order-topic" is the Kafka topic name
        // message is the data being sent (currently String)
        kafkaTemplate.send("order-topic", message);
    }
}
