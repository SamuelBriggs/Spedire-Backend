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
import org.springframework.beans.factory.annotation.Value;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

import static com.spedire.Spedire.utils.Constants.JWT_SECRET;

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
        return JWT.require(Algorithm.HMAC512(secret))
                .build().verify(token);
    }

}