package com.example.taskmanager.util;

import java.util.Random;

public class OtpUtil {
    public static String generateOtp() {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000); // Generate a 6-digit OTP    
        return String.valueOf(otp);
    }
}
