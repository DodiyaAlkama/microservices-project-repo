package com.microservices.user.controller;

import com.microservices.user.dto.AuthResponse;
import com.microservices.user.dto.LoginRequest;
import com.microservices.user.dto.RegisterRequest;
import com.microservices.user.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public String register(
            @RequestBody RegisterRequest request){

        authService.register(request);

        return "User Registered";
    }

    @PostMapping("/login")
    public AuthResponse login(
            @RequestBody LoginRequest request){

        String token = authService.login(request);

        return new AuthResponse(token);
    }
}