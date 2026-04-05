package com.example.demo.controller;

import com.example.demo.dto.LoginRequest;
import com.example.demo.entity.User;
import com.example.demo.service.AuthService;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // ============================
    // 🔐 REGISTER API
    // ============================
    @PostMapping("/register")
    public Map<String, Object> register(@RequestBody User user) {
        User savedUser = authService.register(user);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "User registered successfully");
        response.put("user", savedUser);

        return response;
    }

    // ============================
    // 🔐 LOGIN API
    // ============================
    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody LoginRequest request) {
        User user = authService.login(request.getEmail(), request.getPassword());

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Login successful");
        response.put("user", user);

        return response;
    }

    // ============================
    // 📄 OPTIONAL: GET LOGIN PAGE
    // ============================
    @GetMapping("/login")
    public String loginPage() {
        return "This is login API. Use POST /auth/login to login.";
    }

    // ============================
    // 📄 OPTIONAL: GET REGISTER PAGE
    // ============================
    @GetMapping("/register")
    public String registerPage() {
        return "This is register API. Use POST /auth/register to register.";
    }
}