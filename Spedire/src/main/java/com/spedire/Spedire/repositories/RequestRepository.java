package com.spedire.Spedire.repositories;

import com.spedire.Spedire.models.Request;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RequestRepository extends MongoRepository<Request, String> {
}
