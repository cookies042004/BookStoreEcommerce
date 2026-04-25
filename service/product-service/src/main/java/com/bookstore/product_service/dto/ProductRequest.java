package com.bookstore.product_service.dto;

import lombok.Data;

@Data
public class ProductRequest {
    private String name;
    private double price;
}