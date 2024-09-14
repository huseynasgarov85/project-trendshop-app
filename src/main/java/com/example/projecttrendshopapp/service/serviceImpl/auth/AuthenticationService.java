package com.example.projecttrendshopapp.service.serviceImpl.auth;

import com.example.projecttrendshopapp.dto.JwtRequest;
import org.springframework.http.ResponseEntity;

public interface AuthenticationService {
    ResponseEntity<?> login(JwtRequest authenticationRequest);

    ResponseEntity<String> forgotPass(String email);

    ResponseEntity<String> resetPasswordOtp(String otp, String newPassword, String reNewPassword);
}
