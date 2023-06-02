package com.KiyoInteriors.ECommerce.repository;

import com.KiyoInteriors.ECommerce.entity.Category;
import com.KiyoInteriors.ECommerce.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**

The "ProductRepository" interface is a repository interface that extends the MongoRepository interface.
It provides CRUD (Create, Read, Update, Delete) operations for the "Product" entity in a MongoDB database.
The "Product" entity represents a product in a system.
This interface defines several custom query methods for retrieving products based on different criteria.
*/
public interface ProductRepository extends MongoRepository<Product, String> {
    List<Product> findProductsByCategory(Category category);
    List<Product> findProductsByProductNameLikeIgnoreCase(String name);
    List<Product> findProductsByProductNameLikeIgnoreCaseAndCategory(String name, Category category);
    List<Product> findProductsByProductPrizeBetween(Double from, Double to);
}
