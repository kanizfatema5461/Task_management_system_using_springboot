package com.example.taskmanager.dto;

import lombok.Data;

@Data
public class JwtAuthenticationResponce {
    private String message;
    private String token;
    private String refreshToken;
}
