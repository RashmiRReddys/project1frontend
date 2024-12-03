package com.project.tracker.controller;

import com.project.tracker.model.User;
import com.project.tracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User loginRequest) {
        // Find user by email
        User user = userRepository.findByEmail(loginRequest.getEmail());

        // Validate user credentials
        if (user != null && user.getPassword().equals(loginRequest.getPassword())) {
            // Return role and a mock token
            return ResponseEntity.ok(new AuthResponse(user.getEmail(), user.getRole().name(), "sample-token"));
        } else {
            // Invalid credentials
            return ResponseEntity.status(401).body("Invalid email or password");
        }
    }
}

class AuthResponse {
    private String email;
    private String role;
    private String token;

    public AuthResponse(String email, String role, String token) {
        this.email = email;
        this.role = role;
        this.token = token;
    }

    // Getters
    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    public String getToken() {
        return token;
    }
}
