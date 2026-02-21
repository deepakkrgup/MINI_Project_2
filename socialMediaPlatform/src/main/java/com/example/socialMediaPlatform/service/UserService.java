package com.example.socialMediaPlatform.service;

import com.example.socialMediaPlatform.dto.request.*;
import com.example.socialMediaPlatform.dto.response.*;

public interface UserService {

    UserResponseDto register(RegisterRequestDto request);

    String verifyUser(String token);

}
