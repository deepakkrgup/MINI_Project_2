package com.example.socialMediaPlatform.controller;

import com.example.socialMediaPlatform.dto.request.*;
import com.example.socialMediaPlatform.dto.response.*;
import com.example.socialMediaPlatform.service.*;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final AuthService authService;

    @PostMapping("/register")
    public UserResponseDto register(@RequestBody RegisterRequestDto request) {
        return userService.register(request);
    }

    @GetMapping("/verify")
    public String verify(@RequestParam String token) {
        return userService.verifyUser(token);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequestDto request) {
        return authService.login(request);
    }
}
