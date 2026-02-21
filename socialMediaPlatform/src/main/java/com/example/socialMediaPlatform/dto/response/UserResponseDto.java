package com.example.socialMediaPlatform.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
public class UserResponseDto {
    private Long id;
    private String email;
    private String role;
}
