package com.KiyoInteriors.ECommerce.repository;

import com.KiyoInteriors.ECommerce.entity.Category;
import com.KiyoInteriors.ECommerce.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product, String> {
    List<Product> findProductsByCategory(Category category);
    List<Product> findProductsByProductNameLikeIgnoreCase(String name);
    List<Product> findProductsByProductNameLikeIgnoreCaseAndCategory(String name, Category category);
    List<Product> findProductsByProductPrizeBetween(Double from, Double to);
}
