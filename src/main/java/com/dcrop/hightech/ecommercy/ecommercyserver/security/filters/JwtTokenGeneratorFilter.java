package com.dcrop.hightech.ecommercy.ecommercyserver.security.filters;

import com.dcrop.hightech.ecommercy.ecommercyserver.common.contants.JwtContants;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class JwtTokenGeneratorFilter extends OncePerRequestFilter {


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (Objects.nonNull(authentication)) {
            SecretKey secretKey = Keys.hmacShaKeyFor(JwtContants.JWT_KEY.getBytes(StandardCharsets.UTF_8));
            String jwt = Jwts
                    .builder()
                    .setIssuer("Eazy Bank")
                    .setSubject("Jwt Token")
                    .claim("username", authentication.getName())
                    .claim("authorities", populateAuthorities(authentication.getAuthorities()))
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(new Date().getTime() + 30000000))
                    .signWith(secretKey)
                    .compact();
            response.setHeader(JwtContants.JWT_HEADER, jwt);
        }

        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return request.getServletPath().equals("/user");
    }

    private Object populateAuthorities(Collection<? extends GrantedAuthority> authorities) {

        Set<String> authoriSet = new HashSet<>();
        for (GrantedAuthority authority : authorities) {
            authoriSet.add(authority.getAuthority());
        }

        return String.join(",", authoriSet);
    }


}
