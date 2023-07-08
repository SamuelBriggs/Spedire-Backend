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
   @Value("${sendinblue.api.key}")
    private String mailApiKey;
     @Value("${jwt.signing.secret}")
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

    @Bean
    public String string(){
        return new String();
    }
}