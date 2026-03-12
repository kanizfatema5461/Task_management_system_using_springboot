package com.example.taskmanager.service;

public interface EmailService {
    void sendVerificationEmail(String to, String token);
}
