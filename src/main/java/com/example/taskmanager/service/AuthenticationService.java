package com.example.taskmanager.service;

// import com.example.taskmanager.dto.JwtAthenticationResponce;
import com.example.taskmanager.dto.JwtAuthenticationResponce;
import com.example.taskmanager.dto.RefreshTokenRequest;
import com.example.taskmanager.dto.SigninRequest;
import com.example.taskmanager.dto.SignupRequest;
// import com.example.taskmanager.entity.User;


public interface AuthenticationService {

    // User signup(SignupRequest signupRequest);

    void signup(SignupRequest signupRequest);

    JwtAuthenticationResponce signin(SigninRequest signinRequest);

    JwtAuthenticationResponce refreshToken(RefreshTokenRequest refreshTokenRequest);

    String verifyUser(String token);

    String deleteUser(Long id);
}
