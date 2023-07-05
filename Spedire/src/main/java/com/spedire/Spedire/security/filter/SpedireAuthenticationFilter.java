package com.spedire.Spedire.security.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spedire.Spedire.security.JwtUtil;
import com.spedire.Spedire.dtos.request.LoginRequest;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.Instant;
import java.util.*;

import static com.spedire.Spedire.AppUtils.ExceptionUtils.BADCREDENTIALSEXCEPTION;
import static java.time.Instant.now;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@AllArgsConstructor
@Slf4j
public class SpedireAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;

    private final JwtUtil jwtUtil;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private String phoneNumber = null;

    private String password = null;

    @Override

    public Authentication attemptAuthentication (HttpServletRequest request, HttpServletResponse response){

        try {
            LoginRequest loginRequest = objectMapper.readValue(request.getInputStream(), LoginRequest.class);
            phoneNumber = loginRequest.getPhoneNumber();
            password = loginRequest.getPassword();
            Authentication authentication = new UsernamePasswordAuthenticationToken(phoneNumber, password);
            Authentication authResult = authenticationManager.authenticate(authentication);
            SecurityContextHolder.getContext().setAuthentication(authResult);
            return authResult;

        } catch (IOException e) {
            throw new BadCredentialsException(BADCREDENTIALSEXCEPTION);
        }
    }
    @Override
    public void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                         FilterChain chain, Authentication authResult
                                         ) throws IOException {
        String accessToken = generateAccessToken(authResult.getAuthorities(), request);
        response.setContentType(APPLICATION_JSON_VALUE);
        response.getOutputStream().write(objectMapper.writeValueAsBytes(Map.of("access_token", accessToken)));
    }

    private String generateAccessToken(Collection<? extends GrantedAuthority> authorities, HttpServletRequest request ) throws IOException {
        Map<String, String > map = new HashMap<>();
        for (GrantedAuthority authority: authorities){
            map.put("role", authority.getAuthority());
        }
        return JWT.create().withIssuedAt(now()).
                withExpiresAt(now().plusSeconds(120000L)).
                withClaim("Roles", map).withClaim("phoneNumber", phoneNumber).
                sign(Algorithm.HMAC512("samuel".getBytes()));

    }


}
