package com.spedire.Spedire.controllers;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.spedire.Spedire.dtos.request.RegistrationRequest;
import com.spedire.Spedire.dtos.response.ApiResponse;
import com.spedire.Spedire.dtos.response.ApiResponse;
import com.spedire.Spedire.dtos.request.UpdateUserRequest;
import com.spedire.Spedire.exceptions.SpedireException;
import com.spedire.Spedire.models.User;
import com.spedire.Spedire.security.JwtUtils;
import com.spedire.Spedire.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static java.time.Instant.now;

@RestController
@RequestMapping("/api/v1/user")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    private final JwtUtils jwtUtil;

    @PostMapping("/test")
    public String test(){
        return "this is working mafo";
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestHeader ("Authorization") String token, @RequestBody RegistrationRequest registrationRequest) {

        try {
            var response = userService.register(token, registrationRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (SpedireException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/checkUserExistence")
    public ResponseEntity<?> findUserByPhoneNumber(@RequestParam("phoneNumber") String phoneNumber) throws SpedireException {
        try {
            boolean response = userService.findUserByPhoneNumber(phoneNumber);
                return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (SpedireException e) {
            return ResponseEntity.badRequest().body("User with the provided email already exists, Kindly login");
        }
    }

    @GetMapping("/getCurrentUser")
    public ResponseEntity<?> getCurrentUser(){

        String phoneNumber = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        String newPhoneNumber = phoneNumber.substring(1, phoneNumber.length()-1);
        ApiResponse<?> apiResponse = null;
        try {
            apiResponse = userService.getCurrentUser(newPhoneNumber);
        } catch (SpedireException e) {
            throw new RuntimeException();
        }
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);

    }
    @GetMapping("/findByEmail")
    public ResponseEntity<?> findByEmail(@RequestParam("email") String email) throws SpedireException {
        try {
            User response = userService.findUserByEmail(email);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (SpedireException e) {
            return ResponseEntity.badRequest().body("User with the provided email already exists, Kindly login");
        }
    }

    @PatchMapping("updateProfile")
    public ResponseEntity<?> updateProfile(@RequestParam String id, @ModelAttribute UpdateUserRequest updateUserRequest){
        try{
            var response = userService.updateUserDetails(id, updateUserRequest);
            return ResponseEntity.ok(response);
        }catch (Exception exception){
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @PostMapping("/buildToken")

    public String buildToken(){
        return JWT.create().withIssuedAt(now()).withExpiresAt(now().plusSeconds(120000L)).withClaim
                        ("phoneNumber", "09051243133").withClaim("Roles", List.of("USERS")).
                sign(Algorithm.HMAC512(jwtUtil.getSecret().getBytes()));


    }
}
