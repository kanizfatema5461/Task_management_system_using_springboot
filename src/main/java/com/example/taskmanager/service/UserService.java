package com.example.taskmanager.service;

// import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

// import com.example.taskmanager.entity.User;

public interface UserService {
    UserDetailsService userDetailsService();

    String deleteUser(Long id);


    // forgot password related methods
    void sendOtp(String email);
    boolean verifyOtp(String email, String otp);
    void resetPassword(String email, String newPassword);

}
