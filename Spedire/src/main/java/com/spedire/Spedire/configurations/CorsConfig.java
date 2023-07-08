package com.spedire.Spedire.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

<<<<<<< HEAD
 //   @Bean
  //  public WebMvcConfigurer corsConfigurer() {
   //     return new WebMvcConfigurer() {
    //        @Override
      //      public void addCorsMappings(CorsRegistry registry) {
        //        registry.addMapping("/api/v1/**")
          //              .allowedOrigins("http://localhost:3001")
            //            .allowedMethods("GET", "POST");
           // }
        //};
//    }
=======
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowedMethods("*");
            }
        };
    }
>>>>>>> ce8b91e6dd16e1bf16139c1c783b7a6485a5c13d
}
