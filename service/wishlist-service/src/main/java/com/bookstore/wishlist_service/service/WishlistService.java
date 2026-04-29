package com.bookstore.wishlist_service.service;

import com.bookstore.wishlist_service.entity.Wishlist;
import com.bookstore.wishlist_service.exception.WishlistException;
import com.bookstore.wishlist_service.feign.ProductClient;
import com.bookstore.wishlist_service.repository.WishlistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WishlistService {

    private final WishlistRepository repo;
    private final ProductClient productClient;

    public Wishlist addToWishlist(Long userId, Long productId) {

        // Validate product exists (Feign call)
        try {
            productClient.getProduct(productId);
        } catch (Exception e) {
            throw new WishlistException("Product not found");
        }

        Wishlist wishlist = new Wishlist();
        wishlist.setUserId(userId);
        wishlist.setProductId(productId);

        return repo.save(wishlist);
    }

    public List<Wishlist> getWishlist(Long userId) {
        return repo.findByUserId(userId);
    }
}
