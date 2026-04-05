package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Register a new user
     *
     * @param user User entity with email, password, name, role, etc.
     * @return saved User
     */
    public User register(User user) {
        // Check if email already exists
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        // For now, password is stored as plain text (later encrypt with BCrypt)
        return userRepository.save(user);
    }

    /**
     * Login a user
     *
     * @param email user's email
     * @param password user's password
     * @return authenticated User
     */
    public User login(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Simple password check (plain text)
        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Invalid password");
        }

        return user;
    }
}