package com.example.socialMediaPlatform.service.impl;

import com.example.socialMediaPlatform.entity.*;
import com.example.socialMediaPlatform.repository.*;
import com.example.socialMediaPlatform.service.*;
import com.example.socialMediaPlatform.dto.request.*;
import com.example.socialMediaPlatform.dto.response.*;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponseDto register(RegisterRequestDto request) {

        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ROLE_USER)
                .verificationStatus(VerificationStatus.PENDING)
                .verificationToken(UUID.randomUUID().toString())
                .build();

        userRepository.save(user);

        return UserResponseDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .role(user.getRole().name())
                .build();
    }

    @Override
    public String verifyUser(String token) {

        User user = userRepository.findByVerificationToken(token)
                .orElseThrow(() -> new RuntimeException("Invalid token"));

        user.setVerificationStatus(VerificationStatus.VERIFIED);
        user.setVerificationToken(null);

        userRepository.save(user);

        return "User verified successfully";
    }
}
