package com.KiyoInteriors.ECommerce.repository;

import com.KiyoInteriors.ECommerce.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}
