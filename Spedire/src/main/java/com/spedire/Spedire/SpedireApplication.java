package com.spedire.Spedire;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class SpedireApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpedireApplication.class, args);
	}

}
