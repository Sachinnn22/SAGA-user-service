package com.example.userservice.service;

import com.example.userservice.dto.UserRegisterDto;
import com.example.userservice.dto.UserResponseDto;

public interface UserService {
    UserResponseDto registerUser(UserRegisterDto registerDto);
    UserResponseDto getUserById(Long id);
    UserResponseDto getUserByEmail(String email);
}