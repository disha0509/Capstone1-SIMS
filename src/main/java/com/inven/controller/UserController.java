package com.inven.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inven.model.User;
import com.inven.service.UserService;


@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
public ResponseEntity<Map<String, String>> register(@RequestBody Map<String, String> request) {
    String username = request.get("username");
    String password = request.get("password");
    String role = request.getOrDefault("role", "USER");

    userService.registerUser(username, password, role);

    // Return a JSON response
    Map<String, String> response = Map.of(
        "message", "User registered successfully!"
    );

    return ResponseEntity.ok(response);
}

    @PostMapping("/login")
public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, String> request) {
    String username = request.get("username");
    String password = request.get("password");

    User user = userService.login(username, password);

    // Return the role and success message
    Map<String, String> response = Map.of(
        "message", "Login successful!",
        "role", user.getRole()
    );

    return ResponseEntity.ok(response);
}

    
}
