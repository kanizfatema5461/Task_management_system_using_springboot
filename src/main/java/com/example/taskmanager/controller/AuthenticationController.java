package com.example.taskmanager.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.taskmanager.dto.ForgotPasswordRequest;
// import com.example.taskmanager.dto.JwtAthenticationResponce;
import com.example.taskmanager.dto.JwtAuthenticationResponce;
import com.example.taskmanager.dto.RefreshTokenRequest;
import com.example.taskmanager.dto.ResetPasswordRequest;
import com.example.taskmanager.dto.SigninRequest;
import com.example.taskmanager.dto.SignupRequest;
import com.example.taskmanager.dto.SignupResponse;
import com.example.taskmanager.dto.VerifyOtpRequest;
// import com.example.taskmanager.entity.User;
import com.example.taskmanager.service.AuthenticationService;
import com.example.taskmanager.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;






@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor

public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final UserService userService;

    // @PostMapping("/signup")
    // public ResponseEntity<User> signup(@RequestBody @Valid SignupRequest signupRequest) {
    // return ResponseEntity.ok(authenticationService.signup(signupRequest));
    // }

    @PostMapping("/signup")
    public ResponseEntity<SignupResponse> signup(@RequestBody @Valid SignupRequest signupRequest) {
        authenticationService.signup(signupRequest);
        return ResponseEntity.ok(
            new SignupResponse("Signup successful! Please check your email to verify your account.")
        );
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponce> signin(@RequestBody SigninRequest signinRequest) {
        return ResponseEntity.ok(authenticationService.signin(signinRequest));
    }

    @PostMapping("/refreshtoken")
    public ResponseEntity<JwtAuthenticationResponce> refresh(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        return ResponseEntity.ok(authenticationService.refreshToken(refreshTokenRequest));
    }

    // @GetMapping("/verify")
    // public ResponseEntity<String> verifyUser(@RequestParam String token) {

    //     return ResponseEntity.ok(authenticationService.verifyUser(token));
    // }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        return ResponseEntity.ok(authenticationService.deleteUser(id));
    }

    // @PostMapping("/forgot-password")
    // public ResponseEntity<String> forgotPassword(@RequestBody ForgotPasswordRequest request) {
    //     //TODO: process POST request

    //     userService.sendOtp(request.getEmail());
    //     return ResponseEntity.ok("OTP sent to email");
    // }

    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestParam String email){

    return ResponseEntity.ok(authenticationService.forgotPassword(email));

    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(
            @RequestParam String email,
            @RequestParam String otp,
            @RequestParam String newPassword){

    return ResponseEntity.ok(authenticationService.resetPassword(email, otp, newPassword));
}

    

    // @PostMapping("/verify-otp")
    // public ResponseEntity<String> verifyOtp(@RequestBody VerifyOtpRequest request) {
    //     //TODO: process POST request
    
    //     boolean valid = userService.verifyOtp(request.getEmail(), request.getOtp());
    //     if (!valid) {
    //         return ResponseEntity.badRequest().body("Invalid OTP");
    //     }
    //     return ResponseEntity.ok("OTP verified successfully");
    // }

    // @PostMapping("/reset-password")
    // public ResponseEntity<String> resetPassword(@RequestBody ResetPasswordRequest request) {
    //     userService.resetPassword(request.getEmail(), request.getNewPassword());
    //     return ResponseEntity.ok("Password reset successfully");
    // }

    @PostMapping("/verify-email")
    public ResponseEntity<String> verifyEmail(
            @RequestParam String email,
            @RequestParam String otp) {

        return ResponseEntity.ok(authenticationService.verifyOtp(email, otp));
    }

}
     







