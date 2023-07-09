package com.spedire.Spedire.controllers;

import com.spedire.Spedire.dtos.request.RegistrationRequest;
import com.spedire.Spedire.dtos.response.ApiResponse;
import com.spedire.Spedire.dtos.response.RegistrationResponse;
import com.spedire.Spedire.exceptions.SpedireException;
import com.spedire.Spedire.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/user")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")


    public ResponseEntity<?> registerUser(@RequestBody RegistrationRequest request) {

        try {
            RegistrationResponse response = userService.register(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (SpedireException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/checkUserExistence")
    public ResponseEntity<?> findUserByEmail(@RequestParam("email") String email) throws SpedireException {
        try {
            RegistrationResponse response = userService.checkUserExistence(email);
                return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (SpedireException e) {
            return ResponseEntity.badRequest().body("User with the provided email already exists, Kindly login");
        }
    }

    @GetMapping("/getCurrentUser")
    public ResponseEntity<?> getCurrentUser(){

        String phoneNumber = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        String newPhoneNumber = phoneNumber.substring(1, phoneNumber.length()-1);
        ApiResponse apiResponse = null;
        try {
            apiResponse = userService.getCurrentUser(newPhoneNumber);
        } catch (SpedireException e) {
            throw new RuntimeException();
        }
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);

    }





}
