package com.KiyoInteriors.ECommerce.repository;

import com.KiyoInteriors.ECommerce.entity.Cart;
import com.KiyoInteriors.ECommerce.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CartRepository extends MongoRepository<Cart, String> {
    Optional<Cart> findCartByUser(User user);
}
