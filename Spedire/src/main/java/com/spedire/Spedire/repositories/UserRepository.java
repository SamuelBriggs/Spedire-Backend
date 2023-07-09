package com.spedire.Spedire.repositories;

import com.spedire.Spedire.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findUserByPhoneNumber(String phoneNumber);

    User findByPhoneNumber(String phoneNumber);
    User findByEmail(String email);

}
