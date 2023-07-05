package com.spedire.Spedire.controllers;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class DummyController {

    @PostMapping("/welcome")
    public String welcome(){
        return "Welcome user";
    }

    @PostMapping("/hello")

    public String Hello(){
        return "Hello there";
    }


}
