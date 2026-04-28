package com.bookstore.customer_service.controller;

import com.bookstore.cart_service.entity.Customer;
import com.bookstore.customer_service.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService service;

    @PostMapping
    public Customer save(@RequestBody Customer customer) {
        return service.save(customer);
    }

    @GetMapping("/{userId}")
    public Customer get(@PathVariable Long userId) {
        return service.getByUserId(userId);
    }
}