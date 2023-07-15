package com.spedire.Spedire.repositories;

import com.spedire.Spedire.models.Admin;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AdminRepository extends MongoRepository<Admin, String> {
}
