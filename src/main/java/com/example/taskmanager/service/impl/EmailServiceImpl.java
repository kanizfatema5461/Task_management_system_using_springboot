package com.example.taskmanager.service.impl;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.taskmanager.service.EmailService;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    // @Override
    // public void sendVerificationEmail(String to, String token) {
    
    //     String verificationUrl =
    //             "http://localhost:8080/api/v1/auth/verify?token=" + token;

    //     SimpleMailMessage message = new SimpleMailMessage();
    //     message.setTo(to);
    //     message.setSubject("Verify your email");
    //     message.setText("Click here to verify: \n" + verificationUrl);

    //     mailSender.send(message);
    
    // }


    @Override
    public void sendOtpEmail(String toEmail, String otp) {


        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Your OTP for Password Reset");
        message.setText("Your OTP for password reset is: " + otp + "\nThis OTP is valid for 5 minutes.");

        mailSender.send(message);
    }








    // @Override
    // public void sendEmail(String to, String subject, String body) {
    //     SimpleMailMessage message = new SimpleMailMessage();
    //     message.setTo(to);
    //     message.setSubject(subject);
    //     message.setText(body);

    //     mailSender.send(message);
    // }
}
                 