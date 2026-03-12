package com.example.taskmanager.service;

import java.util.HashMap;

import org.springframework.security.core.userdetails.UserDetails;

// import com.example.taskmanager.entity.User;

public interface JwtService {
    String extractUsername(String token);

    String generateToken(UserDetails userDetails);

    boolean isTokenValid(String token, UserDetails userDetails);

    String generateRefreshToken(HashMap<String, Object> extraClaims, UserDetails userDetails);
}
 