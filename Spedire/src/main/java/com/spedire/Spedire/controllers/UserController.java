package com.spedire.Spedire.controllers;

import com.spedire.Spedire.dtos.request.RegistrationRequest;
import com.spedire.Spedire.dtos.response.RegistrationResponse;
import com.spedire.Spedire.exceptions.SpedireException;
import com.spedire.Spedire.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3001", methods = {RequestMethod.GET, RequestMethod.POST})
@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegistrationRequest request) throws SpedireException {
            RegistrationResponse response = userService.register(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/checkUserExistence")
    public ResponseEntity<?> findUserByEmail(@RequestParam("email") String email) throws SpedireException {
            RegistrationResponse response = userService.checkUserExistence(email);
                return ResponseEntity.status(HttpStatus.OK).body(response);

    }

}
