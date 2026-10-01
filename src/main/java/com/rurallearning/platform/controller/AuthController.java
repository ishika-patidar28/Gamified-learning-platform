
package com.rurallearning.platform.controller;

import com.rurallearning.platform.dto.LoginRequest;
import com.rurallearning.platform.dto.LoginResponse;
import com.rurallearning.platform.entity.User;
import com.rurallearning.platform.services.AuthService;
import com.rurallearning.platform.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final AuthService authService;

    public AuthController(UserService userService,
                          AuthService authService) {

        this.userService = userService;
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {

        User registeredUser = userService.registerUser(user);

        registeredUser.setPassword(null);

        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @RequestBody LoginRequest request) {

        return ResponseEntity.ok(
                authService.login(request)
        );
    }
}