package com.example.userservice.dto;

import lombok.Data;

@Data
public class UserLoginDto {
    private String email;
    private String password;
}