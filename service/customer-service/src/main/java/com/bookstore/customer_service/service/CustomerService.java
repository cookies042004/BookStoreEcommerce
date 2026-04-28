package com.bookstore.customer_service.service;


import com.bookstore.cart_service.entity.Customer;
import com.bookstore.customer_service.repository.CustomerRepository;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository repo;

    public Customer save(Customer customer) {
        return repo.save(customer);
    }

    public Customer getByUserId(Long userId) {
        return repo.findByUserId(userId)
                .orElseThrow(() -> new NotFoundException("Customer not found"));
    }
}