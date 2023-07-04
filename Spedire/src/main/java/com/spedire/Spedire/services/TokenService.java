package com.spedire.Spedire.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.spedire.Spedire.models.User;

import java.time.Instant;

import static com.spedire.Spedire.utils.Constants.ID;
import static java.time.Instant.now;
public class TokenService {


    public static String generateToken(User user, String secret){
        return JWT.create()
                .withIssuedAt(Instant.from(now()))
                .withExpiresAt(now().plusSeconds(300L))
                .withClaim(ID, user.getId())
                .sign(Algorithm.HMAC512(secret.getBytes()));
    }

}
