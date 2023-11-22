package com.dcrop.hightech.ecommercy.ecommercyserver.controllers;

import com.dcrop.hightech.ecommercy.ecommercyserver.controllers.requests.AuthRequest;
import com.dcrop.hightech.ecommercy.ecommercyserver.services.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/sign-in")
    public ResponseEntity<String> login(@RequestBody AuthRequest request) {
        return new ResponseEntity<>(authService.login(request), HttpStatus.CREATED);
    }

    @PostMapping("/sign-up")
    public ResponseEntity<String> register(@RequestBody AuthRequest request) {
        authService.register(request);

        return new ResponseEntity<>("Registration is successfully!", HttpStatus.CREATED);
    }

    @GetMapping
    public String hello() {
        return "Welcome to home page";
    }
}
