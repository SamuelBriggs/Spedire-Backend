package com.spedire.Spedire.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.spedire.Spedire.Exception.SpedireException;
import com.spedire.Spedire.models.Role;
import com.spedire.Spedire.models.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.time.LocalTime.now;

@AllArgsConstructor
@Getter
@Slf4j
public class JwtUtils {
    private final String secret;

    public  Map<String, Claim> extractClaimsFromToken(String token) throws SpedireException {
        DecodedJWT decodedJwt = validateToken(token);
            return decodedJwt.getClaims();
    }
    private DecodedJWT validateToken(String token){

        return JWT.require(Algorithm.HMAC512(secret.getBytes()))
                .build().verify(token);
    }

    public DecodedJWT verifyToken(String token) {
            Algorithm algorithm = Algorithm.HMAC512(secret.getBytes());
            JWTVerifier verifier = JWT.require(algorithm).build();
            return verifier.verify(token);
        }

    public String generateAccessToken(User user, Role role){
        var listOfCurrentUserRoles = user.getRoles();
        listOfCurrentUserRoles.add(role);
        Map<String, String> map = new HashMap<>();
        int number = 1;
        for (int i = 0; i < listOfCurrentUserRoles.size(); i++) {
            map.put("role"+number, listOfCurrentUserRoles.toArray()[i].toString());
            number++;
        }
        return JWT.create().withIssuedAt(Instant.now()).withExpiresAt(Instant.now().plusSeconds(86000L)).
                withClaim("Roles", map).withClaim("userId", user.getId()).sign(Algorithm.HMAC512(secret.getBytes()));


    }
}