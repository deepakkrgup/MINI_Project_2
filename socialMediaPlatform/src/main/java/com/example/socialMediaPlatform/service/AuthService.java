package com.example.socialMediaPlatform.service;

import com.example.socialMediaPlatform.dto.request.LoginRequestDto;

public interface AuthService {
    String login(LoginRequestDto request);
}