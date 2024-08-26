package com.example.projecttrendshopapp.util;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class OtpUtil {
    public String otpGenerated() {
        Random random = new Random();
        int number = 100000 + random.nextInt(900000);
        return Integer.toString(number);
    }
}
