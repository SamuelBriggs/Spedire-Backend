package com.spedire.Spedire.configurations;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.spedire.Spedire.utils.Constants.*;

@Getter
@Configuration
@Slf4j
public class BeanConfig {
   @Value(SENDINBLUE_API_KEY)
    private String mailApiKey;
<<<<<<< HEAD


     @Value(JWT_SECRET)

=======
   @Value(JWT_SECRET)
>>>>>>> 1ffd05eddb23e8efb0ee49a79b6f6df9b2c46e0b
    private String jwt_secret;
    @Value(CLOUDINARY_API_KEY)
    private String apiKey;
    @Value(CLOUDINARY_API_SECRET)
    private String apiSecret;
    @Value(CLOUDINARY_CLOUD_NAME)
    private String cloudName;


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
    public Cloudinary cloudinary(){
        return new Cloudinary(ObjectUtils.asMap(
                CLOUD_NAME_VALUE, cloudName,
                CLOUD_API_KEY_VALUE, apiKey,
                API_SECRET_VALUE, apiSecret
        ));
    }
}