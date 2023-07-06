package com.spedire.Spedire.controllers;


import com.spedire.Spedire.models.User;
import com.spedire.Spedire.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/user")
@Slf4j
public class DummyController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/welcome")
    public String welcome(){
        return "Welcome user";
    }

    @PostMapping("/hello")

    public String Hello(){
        return "Hello there";
    }

    @PostMapping("/detail")

    public String detail(){


        String phoneNumber = Principal.class.getName();

        User user1 =  userRepository.findByPhoneNumber(phoneNumber);

        return "issokay o";


    }


}
