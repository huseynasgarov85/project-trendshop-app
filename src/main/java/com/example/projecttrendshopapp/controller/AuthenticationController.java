package com.example.projecttrendshopapp.controller;

import com.example.projecttrendshopapp.model.dto.JwtRequest;
import com.example.projecttrendshopapp.model.dto.JwtResponse;
import com.example.projecttrendshopapp.service.services.AuthenticationService;
import com.example.projecttrendshopapp.service.services.SecurityService;
import com.example.projecttrendshopapp.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final SecurityService userDetailService;
    private final AuthenticationService authenticationService;

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        log.info("ActionLog.createAuthenticationToken.started : authenticationRequest {}", authenticationRequest);
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new Exception("incorrect username or password", e);
        }
        final UserDetails userDetails = userDetailService.loadUserByUsername(authenticationRequest.getUsername());

        final String jwt = jwtTokenUtil.generateToken(userDetails);
        JwtResponse jwtResponse = new JwtResponse(jwt);
        log.info("ActionLog.createAuthenticationToken.end : authenticationRequest {}", authenticationRequest);
        return ResponseEntity.ok(jwtResponse);
    }

    @PostMapping("/forgotPassword")
    public ResponseEntity<String> forgotPass(String email) {
        return authenticationService.forgotPass(email);
    }

    @PostMapping("/resetPassword")
    public ResponseEntity<String> resetPasswordOtp(String otp, String newPassword, String reNewPassword) {
        return authenticationService.resetPasswordOtp(otp,newPassword,reNewPassword);
    }
}
