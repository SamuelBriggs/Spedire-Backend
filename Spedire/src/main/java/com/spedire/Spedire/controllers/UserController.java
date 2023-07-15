package com.spedire.Spedire.controllers;
;
import com.spedire.Spedire.dtos.request.*;
import com.spedire.Spedire.dtos.response.ForgotPasswordResponse;
import com.spedire.Spedire.dtos.response.PasswordResetResponse;
import com.spedire.Spedire.dtos.response.RegistrationResponse;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.spedire.Spedire.dtos.request.RegistrationRequest;
import com.spedire.Spedire.dtos.response.ApiResponse;
import com.spedire.Spedire.exceptions.SpedireException;
import com.spedire.Spedire.models.User;
import com.spedire.Spedire.security.JwtUtils;
import com.spedire.Spedire.services.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

import static java.time.Instant.now;

@RestController
@RequestMapping("/api/v1/user")
@Slf4j

@AllArgsConstructor
public class UserController {
    private final UserService userService;

    private final JwtUtils jwtUtil;

    @PostMapping("/register")

    public ResponseEntity<?> registerUser(@RequestHeader ("Authorization") String token, @RequestBody RegistrationRequest registrationRequest) throws SpedireException {
        try {
            var response = userService.register(token, registrationRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (SpedireException exception) {
            throw new SpedireException(exception.getMessage());
        }
    }

        @GetMapping("/checkUserExistence")

        public ResponseEntity<?> findUserByPhoneNumber (@RequestParam("phoneNumber") String phoneNumber) throws
        SpedireException {
            try {
                boolean response = userService.findUserByPhoneNumber(phoneNumber);

                return ResponseEntity.status(HttpStatus.OK).body(response);
            } catch (SpedireException exception) {
                throw new SpedireException(exception.getMessage());
            }
        }


            @PostMapping("/forgotPassword")
            public ResponseEntity<?> forgotPassword (@RequestBody ForgotPasswordRequest request) throws SpedireException
            {
                ForgotPasswordResponse response = userService.forgotPassword(request);
                return ResponseEntity.status(HttpStatus.CREATED).body(response);
            }

            @PostMapping("/resetPassword")
            public ResponseEntity<?> resetPassword (@RequestBody PasswordResetRequest request) throws SpedireException {
            PasswordResetResponse response = userService.resetPassword(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }

            @GetMapping("/getCurrentUser")
            public ResponseEntity<?> getCurrentUser () {

            String userId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();

            String newUserId = userId.substring(1, userId.length() - 1);
            ApiResponse<?> apiResponse = null;
            try {
                apiResponse = userService.getCurrentUser(newUserId);
            } catch (SpedireException e) {
                throw new RuntimeException();
            }
            return ResponseEntity.status(HttpStatus.OK).body(apiResponse);

        }
            @GetMapping("/findByEmail")
            public ResponseEntity<?> findByEmail (@RequestParam("email") String email) throws SpedireException {
            try {
                User response = userService.findUserByEmail(email);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            } catch (SpedireException e) {
                return ResponseEntity.badRequest().body("User with the provided email already exists, Kindly login");
            }
        }

            @PatchMapping("/updateProfile")
            public ResponseEntity<?> updateProfile (@RequestHeader("Authorization") String
            token, @ModelAttribute UpdateUserRequest updateUserRequest){
            try {
                var response = userService.updateUserDetails(token, updateUserRequest);
                return ResponseEntity.ok(response);
            } catch (Exception exception) {
                return ResponseEntity.badRequest().body(exception.getMessage());
            }
        }

            @PostMapping("/buildToken")
            public String buildToken () {
            return JWT.create().withIssuedAt(now()).withExpiresAt(now().plusSeconds(120000L)).withClaim
                            ("phoneNumber", "09051243133").withClaim("Roles", List.of("USERS")).
                    sign(Algorithm.HMAC512(jwtUtil.getSecret().getBytes()));


        }

            @PostMapping("upgrade")

            public ResponseEntity<?> upgradeUser (@RequestBody UpgradeUserRequest upgradeUserRequest){

            var response = userService.upgradeUserToCarrier(upgradeUserRequest);

            return ResponseEntity.status(HttpStatus.OK).body(response);


        }
        }
