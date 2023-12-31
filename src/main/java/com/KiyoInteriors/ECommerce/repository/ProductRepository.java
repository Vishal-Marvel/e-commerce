package com.KiyoInteriors.ECommerce.repository;

import com.KiyoInteriors.ECommerce.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**

The "ProductRepository" interface is a repository interface that extends the MongoRepository interface.
It provides CRUD (Create, Read, Update, Delete) operations for the "Product" entity in a MongoDB database.
The "Product" entity represents a product in a system.
This interface defines several custom query methods for retrieving products based on different criteria.
*/
public interface ProductRepository extends MongoRepository<Product, String> {
    List<Product> findAllByCouponsContains(String couponCode);
    Page<Product> findAll(Pageable pageable);
}
