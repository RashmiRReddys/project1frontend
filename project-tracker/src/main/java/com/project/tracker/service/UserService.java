package com.project.tracker.service;

import com.project.tracker.model.User;
import com.project.tracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(Long id, User user) {
        User existingUser = userRepository.findById(id).orElseThrow(() -> 
            new RuntimeException("User not found with ID: " + id));
        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());
        existingUser.setRole(user.getRole());
        return userRepository.save(existingUser);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public User authenticate(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user != null && verifyPassword(password, user.getPassword())) {
            return user;
        }
        return null;
    }

    // Helper method to verify hashed passwords
    private boolean verifyPassword(String rawPassword, String hashedPassword) {
        // Use a secure password encoder like BCryptPasswordEncoder
        return rawPassword.equals(hashedPassword); // Replace with encoder.matches(rawPassword, hashedPassword)
    }
}
