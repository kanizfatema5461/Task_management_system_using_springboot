package com.example.taskmanager.service.impl;
import java.time.LocalDateTime;
import java.util.Random;

// import org.springframework.context.annotation.Lazy;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Lazy;
// import org.springframework.security.authentication.AuthenticationManager;
// import org.hibernate.validator.internal.util.stereotypes.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.taskmanager.entity.User;
import com.example.taskmanager.repository.UserRepository;
import com.example.taskmanager.service.EmailService;
import com.example.taskmanager.service.UserService;

import lombok.RequiredArgsConstructor;



@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;

 

    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) {
                return userRepository.findByEmail(username)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));
            }
        };
    }

    @Override
    public String deleteUser(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setIsDeleted(true); // soft delete

        userRepository.save(user);

        return "User deleted successfully";
    } 


    // forgot password related methods

    private String generateOtp(){
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000); // Generate a 6-digit OTP
        return String.valueOf(otp);
    }

    @Override
    public void sendOtp(String email) {
        var user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));

        String otp = generateOtp();
        user.setOtp(otp);
        user.setOtpExpiryTime(LocalDateTime.now().plusMinutes(5)); // OTP valid for 5 minutes
        userRepository.save(user);

        emailService.sendOtpEmail(email, otp); // Implement this method to send OTP via email
    }

    @Override
    public boolean verifyOtp(String email, String otp) {
        var user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));

        if (user.getOtp() == null)
            return false; // OTP is valid

        if(user.getOtpExpiryTime().isBefore(LocalDateTime.now()))
            return false; // OTP has expired    
        
        return user.getOtp().equals(otp); // OTP matches
    }

    @Override
    public void resetPassword(String email, String newPassword) {
        var user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));

        user.setPassword(passwordEncoder.encode(newPassword)); // Hash the new password
        user.setOtp(null); // Clear OTP after successful password reset
        user.setOtpExpiryTime(null); // Clear OTP expiry time
        userRepository.save(user);
    }
} 
