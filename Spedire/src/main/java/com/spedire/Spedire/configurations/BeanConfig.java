package com.spedire.Spedire.configurations;

import com.spedire.Spedire.utils.JwtUtil;
import lombok.Getter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@Configuration
public class BeanConfig {
//    @Value(CLOUDINARY_API_KEY)
//    private String apiKey;
//    @Value(CLOUDINARY_API_SECRET)
//    private String apiSecret;
//    @Value(CLOUDINARY_CLOUD_NAME)
//    private String cloudName;
    public static final String MAIL_API_KEY="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";
    @Value(MAIL_API_KEY)
    private String mailApiKey;
    public static final String JWT_SIGNING_SECRET="7b0lEBmCZe8D2bUBqtQne0dRcJaT0lML";
    @Value(JWT_SIGNING_SECRET)
    private String jwt_secret;


    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

//    @Bean
//    public Cloudinary cloudinary(){
//        return new Cloudinary(ObjectUtils.asMap(
//                CLOUD_NAME_VALUE, cloudName,
//                CLOUD_API_KEY_VALUE, apiKey,
//                API_SECRET_VALUE, apiSecret
//        ));
//    }

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