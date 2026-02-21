package com.example.socialMediaPlatform.service.impl;

import com.example.socialMediaPlatform.dto.request.LoginRequestDto;
import com.example.socialMediaPlatform.entity.*;
import com.example.socialMediaPlatform.repository.UserRepository;
import com.example.socialMediaPlatform.security.JwtService;
import com.example.socialMediaPlatform.service.AuthService;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public String login(LoginRequestDto request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        if (user.getVerificationStatus() != VerificationStatus.VERIFIED) {
            throw new RuntimeException("Account not verified");
        }

        return jwtService.generateToken(user.getEmail());
    }
}
