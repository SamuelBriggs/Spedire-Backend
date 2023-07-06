package com.spedire.Spedire.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//import static com.spedire.Spedire.AppUtils.SecurityUtils.JWT_SIGNING_SECRET;
@Configuration
public class GeneralConfig {

  //  @Value(JWT_SIGNING_SECRET)
    private String jwt_secret;

    @Bean
    public JwtUtils jwtUtils(){
        return new JwtUtils("samuel");
    }
}
