package com.spedire.Spedire.repositories;

import com.spedire.Spedire.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}
