package com.example.socialMediaPlatform.dto.request;

import lombok.*;

@Getter
@Setter
public class LoginRequestDto {
    private String email;
    private String password;
}
