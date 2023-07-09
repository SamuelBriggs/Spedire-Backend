//package com.spedire.Spedire.utils;
//
//import com.auth0.jwt.JWT;
//import com.auth0.jwt.algorithms.Algorithm;
//import com.auth0.jwt.interfaces.Claim;
//import com.auth0.jwt.interfaces.DecodedJWT;
//import com.spedire.Spedire.exceptions.SpedireException;
//
//import java.util.Map;
//
//public record JwtUtil(String secret) {
//    public static final String CLAIMS_VALUE = "ROLES";
//
//    public Map<String, Claim> extractClaimsFrom(String token) throws SpedireException {
//        validateToken(token);
//        DecodedJWT decodedJwt = validateToken(token);
//        if (decodedJwt.getClaim(CLAIMS_VALUE) == null) throw new SpedireException("");
//        return decodedJwt.getClaims();
//    }
//
//    private DecodedJWT validateToken(String token) {
//        return JWT.require(Algorithm.HMAC512(secret))
//                .build().verify(token);
//    }
//
//}