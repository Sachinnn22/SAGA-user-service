package com.example.userservice.service.impl;

import com.example.userservice.dto.UserLoginDto;
import com.example.userservice.dto.UserRegisterDto;
import com.example.userservice.dto.UserResponseDto;
import com.example.userservice.entity.User;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserResponseDto registerUser(UserRegisterDto dto) {
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Email already exists: " + dto.getEmail());
        }

        User user = User.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .password(dto.getPassword()) 
                .phone(dto.getPhone())
                .role(dto.getRole() != null ? dto.getRole() : "CUSTOMER")
                .build();

        User savedUser = userRepository.save(user);

        return mapToDto(savedUser);
    }

    @Override
    public UserResponseDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        return mapToDto(user);
    }

    @Override
    public UserResponseDto getUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));
        return mapToDto(user);
    }

    @Override
    public UserResponseDto loginUser(UserLoginDto loginDto) {
        // 1. Email එකෙන් user කෙනෙක් ඉන්නවද බලනවා
        User user = userRepository.findByEmail(loginDto.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        // 2. Password එක සමානද කියලා චෙක් කරනවා
        if (!user.getPassword().equals(loginDto.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }

        // 3. සාර්ථක නම් User details (DTO එකක්) හරවනවා
        return mapToDto(user);
    }

    private UserResponseDto mapToDto(User user) {
        return UserResponseDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .role(user.getRole())
                .build();
    }
}