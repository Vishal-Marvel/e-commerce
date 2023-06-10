package com.KiyoInteriors.ECommerce.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import com.KiyoInteriors.ECommerce.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
/**
The "UserRepository" interface is a repository interface that extends the MongoRepository interface.
It provides CRUD (Create, Read, Update, Delete) operations for the "User" entity in a MongoDB database.
The "User" entity represents a user in a system.
This interface defines two custom query methods for retrieving users based on different criteria.
*/
@Repository

public interface UserRepository extends MongoRepository<User, String>
{
    Optional<User> findUserByUsername(String username);

    Optional<User> findUserByUsernameOrEmail(String username, String email);
    Optional<User> findUserByOTP(String otp);
    List<User> findAllByVerified(boolean active);
}
