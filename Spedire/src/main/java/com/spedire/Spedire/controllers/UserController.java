package com.spedire.Spedire.controllers;

import com.spedire.Spedire.dtos.request.RegistrationRequest;
import com.spedire.Spedire.dtos.response.RegistrationResponse;
import com.spedire.Spedire.exceptions.SpedireException;
import com.spedire.Spedire.services.SpedireUserService;
import com.spedire.Spedire.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/users")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/vi/register")
    public ResponseEntity<RegistrationResponse> registerUser(@RequestBody RegistrationRequest request) {
        try {
            RegistrationResponse response = userService.register(request);
            return ResponseEntity.ok(response);
        } catch (SpedireException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
