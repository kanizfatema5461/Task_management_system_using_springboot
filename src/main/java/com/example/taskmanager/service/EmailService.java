package com.example.taskmanager.service;

public interface EmailService {
    // void sendVerificationEmail(String to, String token);

       void sendOtpEmail(String toEmail, String otp);

    // default void sendOtpEmail(String to, String otp) {
    //     String subject = "Your OTP for Password Reset";
    //     String body = "Your OTP for password reset is: " + otp + "\nThis OTP is valid for 5 minutes.";

    //     sendEmail(to, subject, body);
    // }

    // void sendEmail(String to, String subject, String body);
}
