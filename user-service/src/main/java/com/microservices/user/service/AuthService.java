package com.microservices.user.service;

import com.microservices.user.dto.LoginRequest;
import com.microservices.user.dto.RegisterRequest;
import com.microservices.user.entity.User;
import com.microservices.user.repository.UserRepository;
import com.microservices.user.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private JwtUtil jwtUtil;

    public void register(RegisterRequest request){

        User user = new User();

        user.setUsername(request.getUsername());
        user.setPassword(
                encoder.encode(request.getPassword())
        );
        user.setRole("USER");

        repository.save(user);
    }

    public String login(LoginRequest request){

        manager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        return jwtUtil.generateToken(
                request.getUsername()
        );
    }
}