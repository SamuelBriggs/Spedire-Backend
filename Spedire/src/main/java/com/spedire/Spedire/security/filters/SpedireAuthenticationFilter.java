package com.spedire.Spedire.security.filters;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spedire.Spedire.dtos.requests.LoginRequest;
import com.spedire.Spedire.security.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import static java.time.Instant.now;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static com.spedire.Spedire.utils.AppUtils.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequiredArgsConstructor
public class SpedireAuthenticationFilter extends UsernamePasswordAuthenticationFilter  {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String phoneNumber;
        String password;
        try {
            LoginRequest loginRequest = mapper.readValue(request.getInputStream(), LoginRequest.class);
            phoneNumber = loginRequest.getPhoneNumber();
            password = loginRequest.getPassword();

            Authentication authentication = new UsernamePasswordAuthenticationToken(phoneNumber, password);
            Authentication authResult = authenticationManager.authenticate(authentication);
            SecurityContextHolder.getContext().setAuthentication(authResult);
            return authResult;
        } catch (IOException exception) {
            throw new BadCredentialsException(String.format(AUTHENTICATION_FAILED_FOR_USER_WITH_PHONE_NUMBER));
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException {
        String accessToken = generateAccessToken(authResult.getAuthorities());
        response.setContentType(APPLICATION_JSON_VALUE);
        response.getOutputStream().write(mapper.writeValueAsBytes(
                Map.of(ACCESS_TOKEN_VALUE, accessToken)
        ));

    }

    private String generateAccessToken(Collection<? extends GrantedAuthority> authorities) {
        Map<String, String> map = new HashMap<>();
        for (GrantedAuthority authority: authorities) {
            map.put(ROLE, authority.getAuthority());
        }
        return JWT.create()
                .withIssuedAt(now())
                .withExpiresAt(now().plusSeconds(1200L))
                .withClaim(CLAIM_VALUE, map)
                .sign(Algorithm.HMAC512(jwtUtil.getSecret().getBytes()));
    }


}
