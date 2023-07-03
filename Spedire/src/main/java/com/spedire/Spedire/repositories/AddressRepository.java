package com.spedire.Spedire.repositories;

import com.spedire.Spedire.models.Address;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AddressRepository extends MongoRepository<Address, String> {
}
