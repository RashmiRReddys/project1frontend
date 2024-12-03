package com.project.tracker.controller;

import com.project.tracker.dto.LoginResponse;
import com.project.tracker.model.User;
import com.project.tracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
    	System.out.println("i am here users ");
        return userService.getAllUsers();
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
    
//@PostMapping("/login")
//public ResponseEntity<?> login(@RequestBody User loginRequest) {
//    System.out.println("Login endpoint hit"); // Debugging line
//    System.out.println("Email: " + loginRequest.getEmail() + ", Password: " + loginRequest.getPassword());
//
//    User user = userService.authenticate(loginRequest.getEmail(), loginRequest.getPassword());
//
//    if (user != null) {
//        String token = "generated_token_here"; // Replace with actual token logic
//        return ResponseEntity.ok(
//            new LoginResponse(
//                user.getId(),
//                user.getEmail(),
//                user.getRole().toString(), // Convert Role to String
//                token
//            )
//        );
//    } else {
//        return ResponseEntity.status(401).body("Invalid email or password");
//    }
//}   
    
    
}
