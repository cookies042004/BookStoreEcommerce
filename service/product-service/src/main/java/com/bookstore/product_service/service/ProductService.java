package com.bookstore.product_service.service;

import com.bookstore.product_service.dto.ProductRequest;
import com.bookstore.product_service.dto.ProductResponse;
import com.bookstore.product_service.entity.Product;
import com.bookstore.product_service.exception.ProductNotFoundException;
import com.bookstore.product_service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;

    public ProductResponse createProduct(ProductRequest request) {

        Product product = new Product();
        product.setName(request.getName());
        product.setPrice(request.getPrice());

        Product saved = repository.save(product);

        return mapToResponse(saved);
    }

    public ProductResponse getProductById(Long id) {

        Product product = repository.findById(id)
                .orElseThrow(() ->
                        new ProductNotFoundException("Product with id " + id + " not found")
                );

        return mapToResponse(product);
    }

    public List<ProductResponse> getAllProducts() {

        return repository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public void deleteProduct(Long id) {

        if (!repository.existsById(id)) {
            throw new ProductNotFoundException("Product not found");
        }

        repository.deleteById(id);
    }

    private ProductResponse mapToResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .build();
    }
}