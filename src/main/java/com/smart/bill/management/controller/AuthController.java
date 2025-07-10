package com.smart.bill.management.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smart.bill.management.dto.AuthRequest;
import com.smart.bill.management.dto.JwtResponse;
import com.smart.bill.management.dto.RegisterRequest;
import com.smart.bill.management.entity.User;
import com.smart.bill.management.repo.UserRepository;
import com.smart.bill.management.util.JwtUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired private UserRepository userRepo;
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        if (userRepo.existsByEmail(request.email())) {
            return ResponseEntity.badRequest().body("Email already exists");
        }

        User user = new User();
        user.setFullName(request.fullName());
        user.setEmail(request.email());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setRole("USER");

        user = userRepo.save(user);

        String token = jwtUtil.generateToken(user);
        return ResponseEntity.ok(new JwtResponse(token, user.getEmail(), user.getRole()));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        Optional<User> userOpt = userRepo.findByEmail(request.email());
        if (userOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }

        User user = userOpt.get();
        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }

        String token = jwtUtil.generateToken(user);
        return ResponseEntity.ok(new JwtResponse(token, user.getEmail(), user.getRole()));
    }
}
