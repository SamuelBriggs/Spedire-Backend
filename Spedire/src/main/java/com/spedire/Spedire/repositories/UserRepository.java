package com.spedire.Spedire.repositories;

import com.spedire.Spedire.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
<<<<<<< HEAD
    User findByPhoneNumber(String phoneNumber);

    User findByEmail(String email);
=======

    Optional<User> findByPhoneNumber(String username);
>>>>>>> e10467bc7d8031ee3cfb02133d2fff3769db5c0d
}
