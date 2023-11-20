package com.dcrop.hightech.ecommercy.ecommercyserver.services.impl;

import com.dcrop.hightech.ecommercy.ecommercyserver.controllers.requests.AuthRequest;
import com.dcrop.hightech.ecommercy.ecommercyserver.entity.AuthorityEntity;
import com.dcrop.hightech.ecommercy.ecommercyserver.entity.UserEntity;
import com.dcrop.hightech.ecommercy.ecommercyserver.exceptions.ResourcesException;
import com.dcrop.hightech.ecommercy.ecommercyserver.repository.UserRepository;
import com.dcrop.hightech.ecommercy.ecommercyserver.services.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void register(AuthRequest request) {
        Optional<UserEntity> user = userRepository.findByUsername(request.getUsername());
        if (user.isPresent()) {
            throw new ResourcesException("Username has been already in system!");
        }

        UserEntity savedUser = new UserEntity();
        savedUser.setUsername(request.getUsername());
        savedUser.setPassword(passwordEncoder.encode(request.getPassword()));
        savedUser.setRoleDefault();

        userRepository.save(savedUser);
    }

}
