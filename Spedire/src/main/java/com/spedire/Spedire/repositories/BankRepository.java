package com.spedire.Spedire.repositories;

import com.spedire.Spedire.models.Bank;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BankRepository extends MongoRepository<Bank, String> {
}
