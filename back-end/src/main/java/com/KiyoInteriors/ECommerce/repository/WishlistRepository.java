package com.KiyoInteriors.ECommerce.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.KiyoInteriors.ECommerce.entity.Wishlist;
/**
 * A repository interface for managing wishlists in a MongoDB database.
 * This interface extends the MongoRepository interface, providing CRUD operations and query methods for the Wishlist entity.
 */
public interface WishlistRepository extends MongoRepository<Wishlist, String> {
    Optional<Wishlist> findWishlistByUserId(String id);
}
