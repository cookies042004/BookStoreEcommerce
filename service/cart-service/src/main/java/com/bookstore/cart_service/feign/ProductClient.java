package com.bookstore.cart_service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

// Feign Client to communicate with another microservice
// "product-service" must match spring.application.name of Product Service
@FeignClient(name = "product-service")
public interface ProductClient {

    // This will call: http://product-service/products
    // (Eureka will resolve actual host & port automatically)
    @GetMapping("/products")
    String getProducts();
}