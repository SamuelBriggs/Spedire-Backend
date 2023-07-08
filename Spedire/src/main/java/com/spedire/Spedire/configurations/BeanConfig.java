package com.spedire.Spedire.configurations;

import com.spedire.Spedire.utils.JwtUtil;
import lombok.Getter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.spedire.Spedire.utils.Constants.*;

@Getter
@Configuration
public class BeanConfig {
   @Value(SENDIN_BLU_API_KEY)
    private String mailApiKey;
     @Value(JWT_SIGNING_SECRET)
    private String jwt_secret;


    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    @Bean
    public EmailConfig mailConfig(){
        return new EmailConfig(mailApiKey);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtUtil jwtUtil(){
        return new JwtUtil(jwt_secret);
    }


}