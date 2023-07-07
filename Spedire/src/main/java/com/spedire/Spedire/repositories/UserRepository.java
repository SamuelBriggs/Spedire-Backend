package com.spedire.Spedire.repositories;

import com.spedire.Spedire.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
<<<<<<< HEAD
    Optional<User> findUserByPhoneNumber(String phoneNumber);
=======
    User findByPhoneNumber(String phoneNumber);

    User findByEmail(String email);
>>>>>>> 6fbaeba464c189bb1538bf62ba3dc804757c3eb7
}
