package com.spedire.Spedire.security;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
//import static com.spedire.Spedire.AppUtils.SecurityUtils.JWT_SIGNING_SECRET;
  //  @Value(JWT_SIGNING_SECRET)

import static com.spedire.Spedire.security.SecretUtils.JWT_SIGNING_SECRET;

@Configuration
@AllArgsConstructor
public class GeneralConfig {


//    @Value(JWT_SIGNING_SECRET)
//    private String jwt_secret;

    @Bean
    public JwtUtils jwtUtils(){
        return new JwtUtils("samuel");
    }

//    @Bean
//    public static PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

//    @Bean
//    public JwtUtil jwtUtil() {
//        return new JwtUtil(jwt_secret);
//    }
}
