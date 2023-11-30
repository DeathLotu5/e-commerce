package com.dcrop.hightech.ecommercy.ecommercyserver.controllers;

import com.dcrop.hightech.ecommercy.ecommercyserver.controllers.requests.AuthenticationRequest;
import com.dcrop.hightech.ecommercy.ecommercyserver.controllers.requests.RegisterRequest;
import com.dcrop.hightech.ecommercy.ecommercyserver.controllers.responses.AuthenticationResponse;
import com.dcrop.hightech.ecommercy.ecommercyserver.services.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authService.authenticate(request));
    }
}
