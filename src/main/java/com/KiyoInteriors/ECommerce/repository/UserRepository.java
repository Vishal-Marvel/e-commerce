package com.KiyoInteriors.ECommerce.repository;

import java.util.Optional;
import org.springframework.stereotype.Repository;
import com.KiyoInteriors.ECommerce.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

@Repository
public interface UserRepository extends MongoRepository<User, String>
{
    Optional<User> findUserByUsername(String username);

    Optional<User> findUserByUsernameOrEmail(String username, String email);
}