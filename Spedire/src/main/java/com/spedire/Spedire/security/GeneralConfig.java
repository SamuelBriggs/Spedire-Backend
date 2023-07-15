package com.spedire.Spedire.security;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static com.spedire.Spedire.utils.EmailConstants.JWT_SECRET;

@Configuration
@AllArgsConstructor
public class GeneralConfig {
    @Value(JWT_SECRET)
    private String jwt_secret;

    @Bean
    public JwtUtils jwtUtils(){
        return new JwtUtils(jwt_secret);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer(){
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedHeaders("*")
                        .allowedOrigins("*");
            }
        };
    }

}
