package com.dcrop.hightech.ecommercy.ecommercyserver.services.impl;

import com.dcrop.hightech.ecommercy.ecommercyserver.controllers.requests.AuthenticationRequest;
import com.dcrop.hightech.ecommercy.ecommercyserver.controllers.requests.RegisterRequest;
import com.dcrop.hightech.ecommercy.ecommercyserver.controllers.responses.AuthenticationResponse;
import com.dcrop.hightech.ecommercy.ecommercyserver.entity.UserEntity;
import com.dcrop.hightech.ecommercy.ecommercyserver.entity.enums.Role;
import com.dcrop.hightech.ecommercy.ecommercyserver.repositories.UserRepository;
import com.dcrop.hightech.ecommercy.ecommercyserver.services.AuthService;
import com.dcrop.hightech.ecommercy.ecommercyserver.services.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthenticationResponse register(RegisterRequest request) {
        var user = UserEntity
                .builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        userRepository.save(user);

        return AuthenticationResponse
                .builder()
                .token(jwtService.generateToken(user))
                .build();
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        var user = userRepository.findByEmail(request.getEmail()).orElseThrow();

        return AuthenticationResponse
                .builder()
                .token(jwtService.generateToken(user))
                .build();
    }
}
