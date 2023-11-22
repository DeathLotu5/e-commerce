package com.dcrop.hightech.ecommercy.ecommercyserver.common.utils;

import com.dcrop.hightech.ecommercy.ecommercyserver.common.contants.JwtContants;
import com.dcrop.hightech.ecommercy.ecommercyserver.entity.AuthorityEntity;
import com.dcrop.hightech.ecommercy.ecommercyserver.entity.UserEntity;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class JwtTokenProvider {

    private static final Long EXPIRE_TIME = 30000000L;

    public String generateToken(UserEntity user) {
        SecretKey secretKey = Keys.hmacShaKeyFor(JwtContants.JWT_KEY.getBytes(StandardCharsets.UTF_8));

        Date now = new Date();
        return Jwts
                .builder()
                .setIssuer("Eazy Bank")
                .setSubject("Jwt Token")
                .claim("username", user.getUsername())
                .claim("authorities", populateAuthorities(user.getAuthorities()))
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + EXPIRE_TIME))
                .signWith(secretKey)
                .compact();
    }

    private Object populateAuthorities(List<AuthorityEntity> authorities) {
        Set<String> authoriSet = new HashSet<>();
        for (AuthorityEntity authority : authorities) {
            authoriSet.add(authority.getName());
        }

        return String.join(",", authoriSet);
    }

}
