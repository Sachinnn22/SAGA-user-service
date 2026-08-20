package com.example.userservice.dto;

import lombok.Data;

@Data
public class UserRegisterDto {
    private String name;
    private String email;
    private String password;
    private String phone;
    private String role; // CUSTOMER or SALON_OWNER
}