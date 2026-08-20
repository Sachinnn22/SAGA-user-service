package com.example.userservice.service;

import com.example.userservice.dto.AuthResponseDto;
import com.example.userservice.dto.UserLoginDto;
import com.example.userservice.dto.UserRegisterDto;
import com.example.userservice.dto.UserResponseDto;

public interface UserService {
    UserResponseDto registerUser(UserRegisterDto registerDto);
    UserResponseDto getUserById(Long id);
    UserResponseDto getUserByEmail(String email);
    AuthResponseDto loginUser(UserLoginDto loginDto); // 👈 මෙතන UserResponseDto වෙනුවට AuthResponseDto දාලා තියෙන්නේ
}