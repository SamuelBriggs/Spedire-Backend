package com.spedire.Spedire.controllers;

import com.spedire.Spedire.dtos.request.RegistrationRequest;
import com.spedire.Spedire.dtos.request.UpdateUserRequest;
import com.spedire.Spedire.dtos.response.RegistrationResponse;
import com.spedire.Spedire.exceptions.SpedireException;
import com.spedire.Spedire.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestHeader(value = "token") String token, @RequestBody RegistrationRequest registrationRequest) {
        try {
            var response = userService.register(token, registrationRequest);
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

    @PatchMapping()
    public ResponseEntity<?> updateCustomerAccount(@RequestParam String id, @ModelAttribute UpdateUserRequest updateUserRequest){
        try{
            var response = userService.updateUserDetails(id, updateUserRequest);
            return ResponseEntity.ok(response);
        }catch (Exception exception){
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }
}
