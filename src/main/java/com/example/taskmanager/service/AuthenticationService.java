package com.example.taskmanager.service;

// import com.example.taskmanager.dto.JwtAthenticationResponce;
import com.example.taskmanager.dto.JwtAuthenticationResponce;
import com.example.taskmanager.dto.RefreshTokenRequest;
import com.example.taskmanager.dto.SigninRequest;
import com.example.taskmanager.dto.SignupRequest;
// import com.example.taskmanager.entity.User;


public interface AuthenticationService {

    // User signup(SignupRequest signupRequest);

    // void signup(SignupRequest signupRequest);

    String signup(SignupRequest signupRequest);

    JwtAuthenticationResponce signin(SigninRequest signinRequest);

    JwtAuthenticationResponce refreshToken(RefreshTokenRequest refreshTokenRequest);

    // String verifyUser(String token);

    String deleteUser(Long id);

    String verifyOtp(String email, String otp);

    String forgotPassword(String email);

	String resetPassword(String email, String otp, String newPassword);
}
