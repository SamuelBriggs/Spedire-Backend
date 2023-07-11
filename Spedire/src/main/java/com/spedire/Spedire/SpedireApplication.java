package com.spedire.Spedire;

import com.spedire.Spedire.security.JwtUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import static com.spedire.Spedire.security.SecretUtils.JWT_SIGNING_SECRET;

@SpringBootApplication
@EnableMongoRepositories
@Configuration
public class SpedireApplication {


	public static void main(String[] args) {
		SpringApplication.run(SpedireApplication.class, args);

	}



}
