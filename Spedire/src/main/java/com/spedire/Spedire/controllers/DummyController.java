package com.spedire.Spedire.controllers;


import com.spedire.Spedire.models.User;
import com.spedire.Spedire.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/user")
@Slf4j

@CrossOrigin("http://localhost:3001")
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

    public User detail(){


        String phoneNumber = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();

        System.out.println(phoneNumber);

        log.info("{}",phoneNumber);
        String newPhoneNumber = phoneNumber.substring(1, phoneNumber.length()-1);
        log.info(newPhoneNumber);
        var user1 =  userRepository.findUserByPhoneNumber(newPhoneNumber);
        log.info("{}", userRepository);


        log.info("{}",user1.get());

        return user1.get();


    }


}
