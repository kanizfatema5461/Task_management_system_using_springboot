package com.example.taskmanager.service.impl;

import java.time.LocalDateTime;
import java.util.HashMap;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.crypto.factory.PasswordEncoderFactories;
// import org.sprOtingframework.security.crypto.password.PasswordEncoder;
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
import com.example.taskmanager.util.OtpUtil;

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
    // public void signup(SignupRequest signupRequest) {
    public String signup(SignupRequest signupRequest) {

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

        String otp = OtpUtil.generateOtp();

        // ⭐ Email verification setup
        // String token = java.util.UUID.randomUUID().toString();
        // user.setVerificationToken(token);
        // user.setEnabled(false);

        user.setOtp(otp);
        user.setOtpExpiryTime(LocalDateTime.now().plusMinutes(5));
        user.setEnabled(false);
        user.setEmailVerified(false);


        // User savedUser = userRepository.save(user);

        // // ⭐ Send verification email
        // emailService.sendVerificationEmail(user.getEmail(), token);
        // return savedUser;

        userRepository.save(user);

        emailService.sendOtpEmail(user.getEmail(), otp);
        return "Signup successful! Please check your email to verify your account.";

        // emailService.sendVerificationEmail(user.getEmail(), token);

        // return userRepository.save(user);
    }

    @Override
    public JwtAuthenticationResponce signin(SigninRequest signinRequest) {
    // public JwtAuthenticationResponce signin(SigninRequest signinRequest) {


        String email = signinRequest.getEmail().trim().toLowerCase();

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, signinRequest.getPassword()));
        
        // var user = userRepository.findByEmail(signinRequest.getEmail()).orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
        var user = userRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));

        if(!user.isEmailVerified()){
            throw new RuntimeException("Please verify your email first");
        }

        JwtAuthenticationResponce responce = new JwtAuthenticationResponce();

        if(user.getRole() == Role.ADMIN){

            var jwt = jwtService.generateToken(user);
            var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);
            // JwtAuthenticationResponce jwtAthenticationResponce = new JwtAuthenticationResponce();

            responce.setToken(jwt);
            responce.setRefreshToken(refreshToken);
            responce.setMessage("Signin Sucessfull");


            // jwtAthenticationResponce.setToken(jwt);
            // jwtAthenticationResponce.setRefreshToken(refreshToken);

            // return jwtAthenticationResponce;
        }else {
            responce.setMessage("Signin Successfull");
            responce.setToken(null);
            responce.setRefreshToken(null);
        }

        return responce;
    
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


    // @Override
    // public String verifyUser(String token) {

    //     User user = userRepository.findByVerificationToken(token)
    //             .orElseThrow(() -> new RuntimeException("Invalid token"));

    //     user.setEnabled(true);
    //     user.setVerificationToken(null);

    //     userRepository.save(user);

    //     return "Email verified successfully";
    // }

    public String verifyOtp(String email, String otp) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if(!user.getOtp().equals(otp)){
            throw new RuntimeException("Invalid OTP");
        }

        if(user.getOtpExpiryTime().isBefore(LocalDateTime.now())){
            throw new RuntimeException("OTP expired");
        }

        user.setEnabled(true);
        user.setEmailVerified(true);
        user.setOtp(null);
        user.setOtpExpiryTime(null);

        userRepository.save(user);

        return "Email verified successfully";
    }


    public String forgotPassword(String email){
        User user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new RuntimeException("User not found"));
                
        String otp = OtpUtil.generateOtp();

        user.setOtp(otp);
        user.setOtpExpiryTime(LocalDateTime.now().plusMinutes(5));

        emailService.sendOtpEmail(email, otp);

        userRepository.save(user);

        return "OTP send to email";
    }


    public String resetPassword(String email, String otp, String newPassword){

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if(!user.getOtp().equals(otp)){
            throw new RuntimeException("Invalid OTP");
        }

        if(user.getOtpExpiryTime().isBefore(LocalDateTime.now())){
            throw new RuntimeException("OTP expired");
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        user.setOtp(null);

        userRepository.save(user);

        return "Password reset successful";
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
    