package com.example.userservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponseDto {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String role;
}