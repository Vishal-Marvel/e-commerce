package com.KiyoInteriors.ECommerce.repository;

import com.KiyoInteriors.ECommerce.entity.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;


public interface CategoryRepository extends MongoRepository<Category, String> {
    Optional<Category> findByCategory(String category);
}
