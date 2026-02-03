package com.url.shortner_sb.controller;

import com.url.shortner_sb.dto.LoginRequest;
import com.url.shortner_sb.dto.RegisterRequest;
import com.url.shortner_sb.model.User;
import com.url.shortner_sb.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    // ✅ MANUAL CONSTRUCTOR
    public AuthController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/public/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest registerRequest) {

        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(registerRequest.getPassword());
        user.setRole("ROLE_USER");

        userService.registerUser(user);

        return ResponseEntity.ok("User registered successfully");
    }

    //login now
    @PostMapping("/public/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(userService.authenticateUser(loginRequest));
    }
}
