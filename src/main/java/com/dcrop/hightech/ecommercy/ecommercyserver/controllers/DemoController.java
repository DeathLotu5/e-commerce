package com.dcrop.hightech.ecommercy.ecommercyserver.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/demo")
public class DemoController {

    @GetMapping("/hello")
    public ResponseEntity<String> sayHello() {
        System.out.println("Hello");
        return ResponseEntity.ok("Hello from secured endpoint");
    }

}
