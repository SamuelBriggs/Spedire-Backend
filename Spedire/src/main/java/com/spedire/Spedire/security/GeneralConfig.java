package com.spedire.Spedire.security;

<<<<<<< HEAD
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
=======
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.spedire.Spedire.security.SecretUtils.JWT_SIGNING_SECRET;

@Configuration
@AllArgsConstructor
public class GeneralConfig {


//    @Value(JWT_SIGNING_SECRET)
//    private String jwt_secret;

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public JwtUtil jwtUtil() {
//        return new JwtUtil(jwt_secret);
//    }
>>>>>>> e10467bc7d8031ee3cfb02133d2fff3769db5c0d
}
