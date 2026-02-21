package com.example.socialMediaPlatform.dto.request;

import lombok.*;

@Getter
@Setter
public class RegisterRequestDto {
    private String email;
    private String password;
}