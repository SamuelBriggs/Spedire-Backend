package com.spedire.Spedire.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.spedire.Spedire.Exception.SpedireException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@AllArgsConstructor
@Getter
@Slf4j
public class JwtUtils {
    private final String secret ="samuel";

    public static Map<String, Claim> extractClaimsFromToken(String token) throws SpedireException {
        DecodedJWT decodedJwt = validateToken(token);
            return decodedJwt.getClaims();
    }
    private static DecodedJWT validateToken(String token){
        return JWT.require(Algorithm.HMAC512("samuel".getBytes()))
                .build().verify(token);
    }

}