package com.example.taskmanager.service.impl;

import java.util.HashMap;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.taskmanager.dto.JwtAuthenticationResponce;
import com.example.taskmanager.dto.RefreshTokenRequest;
import com.example.taskmanager.dto.SigninRequest;
import com.example.taskmanager.dto.SignupRequest;
import com.example.taskmanager.entity.Role;
import com.example.taskmanager.entity.User;
import com.example.taskmanager.repository.UserRepository;
import com.example.taskmanager.service.AuthenticationService;
import com.example.taskmanager.service.EmailService;
import com.example.taskmanager.service.JwtService;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    private final EmailService emailService;



    @Override
    // public User signup(SignupRequest signupRequest) 
    public void signup(SignupRequest signupRequest) {

        String email = signupRequest.getEmail().toLowerCase();

        if(userRepository.existsByEmail(signupRequest.getEmail())) {
            throw new IllegalArgumentException("Email already registered");
        }

        User user = new User();
        user.setEmail(email);
        user.setEmail(signupRequest.getEmail());
        user.setFirstname(signupRequest.getFirstname());
        user.setLastname(signupRequest.getLastname());
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));

        // ⭐ Email verification setup
        String token = java.util.UUID.randomUUID().toString();
        user.setVerificationToken(token);
        user.setEnabled(false);

        // User savedUser = userRepository.save(user);

        // // ⭐ Send verification email
        // emailService.sendVerificationEmail(user.getEmail(), token);
        // return savedUser;

        userRepository.save(user);
        emailService.sendVerificationEmail(user.getEmail(), token);

        // return userRepository.save(user);
    }

    @Override
    public JwtAuthenticationResponce signin(SigninRequest signinRequest) {

        String email = signinRequest.getEmail().trim().toLowerCase();

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, signinRequest.getPassword()));
        
        // var user = userRepository.findByEmail(signinRequest.getEmail()).orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
        var user = userRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
        var jwt = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);

        JwtAuthenticationResponce jwtAthenticationResponce = new JwtAuthenticationResponce();

        jwtAthenticationResponce.setToken(jwt);
        jwtAthenticationResponce.setRefreshToken(refreshToken);

        return jwtAthenticationResponce;
    
    }

    public JwtAuthenticationResponce refreshToken(RefreshTokenRequest refreshTokenRequest) {
       String userEmail = jwtService.extractUsername(refreshTokenRequest.getToken());
       User user = userRepository.findByEmail(userEmail).orElseThrow();
       if (jwtService.isTokenValid(refreshTokenRequest.getToken(), user)) { 
        var jwt = jwtService.generateToken(user);

        JwtAuthenticationResponce jwtAthenticationResponce = new JwtAuthenticationResponce();

        jwtAthenticationResponce.setToken(jwt);
        jwtAthenticationResponce.setRefreshToken(refreshTokenRequest.getToken());

        return jwtAthenticationResponce;
        }

        return null;

    }


    @Override
    public String verifyUser(String token) {

        User user = userRepository.findByVerificationToken(token)
                .orElseThrow(() -> new RuntimeException("Invalid token"));

        user.setEnabled(true);
        user.setVerificationToken(null);

        userRepository.save(user);

        return "Email verified successfully";
    }

    @Override
    public String deleteUser(Long id){

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setIsDeleted(true);

        userRepository.save(user);

        return "User deleted (soft delete)";
    }

}
    