package com.KiyoInteriors.ECommerce.repository;

import com.KiyoInteriors.ECommerce.entity.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
/**

The "CategoryRepository" interface is a repository interface that extends the MongoRepository interface.
It provides CRUD (Create, Read, Update, Delete) operations for the "Category" entity in a MongoDB database.
The "Category" entity represents a category of products in a system.
This interface defines a custom query method, "findByCategory", which allows finding a category by its name.
*/

public interface CategoryRepository extends MongoRepository<Category, String> {
    Optional<Category> findByCategoryName(String category);
    boolean existsByCategoryName(String category);

}
