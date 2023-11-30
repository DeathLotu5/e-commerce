package com.dcrop.hightech.ecommercy.ecommercyserver.services;


import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;
import java.util.function.Function;

public interface JwtService {
    String extractUsername(String token);

    <T> T extractClaim(String token, Function<Claims, T> claimsResolver);

    String generateToken(UserDetails user);

    String generateToken(Map<String, Object> extractClaims, UserDetails user);

    boolean isTokenValid(String token, UserDetails user);
}
