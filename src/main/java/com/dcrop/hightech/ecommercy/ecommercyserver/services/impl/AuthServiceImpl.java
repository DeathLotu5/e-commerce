package com.dcrop.hightech.ecommercy.ecommercyserver.services.impl;

import com.dcrop.hightech.ecommercy.ecommercyserver.common.utils.JwtTokenProvider;
import com.dcrop.hightech.ecommercy.ecommercyserver.controllers.requests.AuthRequest;
import com.dcrop.hightech.ecommercy.ecommercyserver.entity.UserEntity;
import com.dcrop.hightech.ecommercy.ecommercyserver.exceptions.ResourcesException;
import com.dcrop.hightech.ecommercy.ecommercyserver.repository.UserRepository;
import com.dcrop.hightech.ecommercy.ecommercyserver.services.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtProvider;

    @Override
    public String login(AuthRequest request) {
        UserEntity user = getUserByUsername(request.getUsername()).orElseThrow(() -> new ResourcesException("Username or password incorrect!"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Username or password incorrect!");
        }

        return jwtProvider.generateToken(user);
    }

    @Override
    public void register(AuthRequest request) {
        if (getUserByUsername(request.getUsername()).isPresent()) {
            throw new ResourcesException("User has registered in system!");
        }

        UserEntity savedUser = new UserEntity();
        savedUser.setUsername(request.getUsername());
        savedUser.setPassword(passwordEncoder.encode(request.getPassword()));
        savedUser.setRoleDefault();

        userRepository.save(savedUser);
    }

    private Optional<UserEntity> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

}
