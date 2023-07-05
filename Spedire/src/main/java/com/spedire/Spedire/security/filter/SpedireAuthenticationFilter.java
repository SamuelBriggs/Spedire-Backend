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

import java.io.IOException;
import java.time.Instant;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static com.spedire.Spedire.AppUtils.ExceptionUtils.BADCREDENTIALSEXCEPTION;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@AllArgsConstructor
@Slf4j
public class SpedireAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;

    private final JwtUtil jwtUtil;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override

    public Authentication attemptAuthentication (HttpServletRequest request, HttpServletResponse response){

        try {
            LoginRequest loginRequest = objectMapper.readValue(request.getInputStream(), LoginRequest.class);
            String phoneNumber = loginRequest.getPhoneNumber();
            String password = loginRequest.getPassword();
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
        String accessToken = generateAccessToken(authResult.getAuthorities());
        response.setContentType(APPLICATION_JSON_VALUE);
        response.getOutputStream().write(objectMapper.writeValueAsBytes(Map.of("access_token", accessToken)));
    }

    private String generateAccessToken(Collection<? extends GrantedAuthority> authorities){
        Map<String, String> map = new HashMap<>();
        for (GrantedAuthority authority: authorities){
            map.put("role", authority.getAuthority());
        }
        return JWT.create().withIssuedAt(Instant.now()).
                withExpiresAt(Instant.now().plusSeconds(120000L)).
                withClaim("Roles", map).sign(Algorithm.HMAC512("samuel".getBytes()));

    }


}
