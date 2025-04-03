package com.example.projecttrendshopapp.controller;

import com.example.projecttrendshopapp.dto.JwtRequest;
import com.example.projecttrendshopapp.service.serviceImpl.auth.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) {
        return authenticationService.login(authenticationRequest);
    }

    @PostMapping("/forgotPassword")
    public ResponseEntity<String> forgotPass(String email) {
        return authenticationService.forgotPass(email);
    }

    @PostMapping("/resetPassword")
    public ResponseEntity<String> resetPasswordOtp(String otp, String newPassword, String reNewPassword) {
        return authenticationService.resetPasswordOtp(otp, newPassword, reNewPassword);
    }
}
