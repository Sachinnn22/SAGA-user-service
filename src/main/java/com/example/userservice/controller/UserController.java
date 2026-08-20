package com.example.userservice.controller;

import com.example.userservice.dto.UserLoginDto;
import com.example.userservice.dto.UserRegisterDto;
import com.example.userservice.dto.UserResponseDto;
import com.example.userservice.dto.ApiResponse;
import com.example.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserResponseDto>> registerUser(@RequestBody UserRegisterDto registerDto) {
        UserResponseDto responseDto = userService.registerUser(registerDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("User registered successfully", responseDto));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<UserResponseDto>> loginUser(@RequestBody UserLoginDto loginDto) {
        UserResponseDto responseDto = userService.loginUser(loginDto);
        return ResponseEntity.ok(ApiResponse.success("Login successful", responseDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserResponseDto>> getUserById(@PathVariable Long id) {
        UserResponseDto responseDto = userService.getUserById(id);
        return ResponseEntity.ok(ApiResponse.success("User fetched successfully", responseDto));
    }

    @GetMapping("/profile")
    public ResponseEntity<ApiResponse<UserResponseDto>> getProfile(@RequestHeader("X-User-Email") String email) {
        UserResponseDto responseDto = userService.getUserByEmail(email);
        return ResponseEntity.ok(ApiResponse.success("Profile fetched successfully", responseDto));
    }
}