package com.spedire.Spedire.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

import static com.spedire.Spedire.utils.Constants.JWT_SECRET;
@Configuration
public class GeneralConfig {

    @Value(JWT_SECRET)
    private String jwt_secret;

    @Bean
    public JwtUtils jwtUtils(){
<<<<<<< HEAD
        return new JwtUtils(jwt_secret);
=======
        return new JwtUtils();
>>>>>>> 1ffd05eddb23e8efb0ee49a79b6f6df9b2c46e0b
    }



  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOrigins(Arrays.asList("*")); // Set allowed origins
    configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE")); // Set allowed HTTP methods
    configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type")); // Set allowed headers

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration); // Apply configuration to all paths

    return source;
  }
}
