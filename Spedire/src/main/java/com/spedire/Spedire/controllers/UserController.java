package com.spedire.Spedire.controllers;

import com.spedire.Spedire.dtos.request.RegistrationRequest;
import com.spedire.Spedire.dtos.response.RegistrationResponse;
import com.spedire.Spedire.exceptions.SpedireException;
import com.spedire.Spedire.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//@CrossOrigin(origins = "http://localhost:3001", methods = {RequestMethod.GET, RequestMethod.POST})
@RestController
@RequestMapping("/api/v1/users")
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
}
