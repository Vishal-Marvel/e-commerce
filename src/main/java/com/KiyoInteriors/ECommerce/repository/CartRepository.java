package com.KiyoInteriors.ECommerce.repository;

import com.KiyoInteriors.ECommerce.entity.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

/**
 * The "CartRepository" interface is a repository interface that extends the
 * MongoRepository interface.
 * It provides CRUD (Create, Read, Update, Delete) operations for the "Cart"
 * entity in a MongoDB database.
 * The "Cart" entity represents a shopping cart, and each cart is associated
 * with a specific user.
 * This interface defines a custom query method, "findCartByUser", which allows
 * finding a cart based on the associated user.
 */
public interface CartRepository extends MongoRepository<Cart, String> {
    Optional<Cart> findCartByUserId(String id);
}
