package com.KiyoInteriors.ECommerce.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.KiyoInteriors.ECommerce.entity.Wishlist;

public interface WishlistRepository extends MongoRepository<Wishlist, String> {
    Optional<Wishlist> findWishlistByUserId(String id);
}
