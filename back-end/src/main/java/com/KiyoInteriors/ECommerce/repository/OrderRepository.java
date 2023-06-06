package com.KiyoInteriors.ECommerce.repository;

import com.KiyoInteriors.ECommerce.entity.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.List;

/**
The OrderRepository interface extends the MongoRepository interface and provides
database access methods for managing Order entities in a MongoDB database.
It defines the following methods:
findAllByUserId(String id): Retrieves a list of orders associated with the given user ID.
This method queries the database and returns all orders that match the specified user ID.
The returned list contains Order objects representing the orders.
The OrderRepository interface allows for easy interaction with the underlying MongoDB
database to perform CRUD (Create, Read, Update, Delete) operations on Order entities.
It inherits common database operations from the MongoRepository interface and also
includes additional custom methods specific to order management.
*/
public interface OrderRepository extends MongoRepository<Order, String> {
    List<Order> findAllByUserId(String id);
    List<Order> findAllByOrderDateAfter(Date date);
}
