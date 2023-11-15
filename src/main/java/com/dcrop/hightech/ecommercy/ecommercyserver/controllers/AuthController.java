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
    public ResponseEntity<String> signIn(@RequestBody AuthRequest request) {
        int response = authService.login(request);
        if (response == 1 ) {
            return new ResponseEntity<>("Logging in successfully!", HttpStatus.CREATED);
        }

        return new ResponseEntity<>("Creating account failed", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/sign-up")
    public ResponseEntity<String> signUp(@RequestBody AuthRequest request) {
        int response = authService.signUp(request);
        if (response == 0 ) {
            return new ResponseEntity<>("You has created successfully account", HttpStatus.CREATED);
        }

        return new ResponseEntity<>("Creating account failed", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello Guys";
    }

}
