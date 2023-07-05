package com.spedire.Spedire.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.spedire.Spedire.Exception.SpedireException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@AllArgsConstructor
@Getter
@Slf4j
public class JwtUtil {
    private final String secret;

    public static Map<String, Claim> extractClaimsFromToken(String token) throws SpedireException {
        DecodedJWT decodedJwt = validateToken(token);
        log.info("have you encoded?" + decodedJwt);
            return decodedJwt.getClaims();
    }

    private Date extractDate(String token){
        return extractClaim(token, Claims::getExpiration);

    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getClaims(token);
        return claimsResolver.apply(claims);
    }

    private static Claims getClaims(String token){
        return Jwts.parserBuilder().setSigningKey("samuel".getBytes()).build().parseClaimsJws(token).getBody();
    }

    private static DecodedJWT validateToken(String token){
        log.info("print this token again " + token);
        boolean checkToken = (token.trim()).equals( "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJpYXQiOjE2ODg1MzEwNzksImV4cCI6MTY4ODY1MTA3OSwiUm9sZXMiOnsicm9sZSI6IlVTRVIifX0.vI8enJ_WykXhm2AT0NzDUWb9J_zT8loX5MUNZo_V-IxZHb0ySrPJQRmcUgIwbBYcFSfPBwU_nl6vI0rDKddrEQ");
        log.info("checking the result here " + checkToken);
        return JWT.require(Algorithm.HMAC512("samuel".getBytes()))
                .build().verify(token);
    }




}