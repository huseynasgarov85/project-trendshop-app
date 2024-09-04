package com.example.projecttrendshopapp.service.services;

import com.example.projecttrendshopapp.dao.entity.OtpEntity;
import com.example.projecttrendshopapp.dao.entity.UsersEntity;
import com.example.projecttrendshopapp.dao.repository.OtpRepository;
import com.example.projecttrendshopapp.dao.repository.UsersRepository;
import com.example.projecttrendshopapp.exception.NotFoundException;
import com.example.projecttrendshopapp.model.enums.OtpStatus;
import com.example.projecttrendshopapp.util.JwtTokenUtil;
import com.example.projecttrendshopapp.util.OtpUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthenticationService {
    private final UsersRepository usersRepository;
    private final OtpUtil otpUtil;
    private final EmailService emailService;
    private final OtpRepository otpRepository;
    private final PasswordEncoder passwordEncoder;

    public ResponseEntity<String> forgotPass(String email) {
        log.info("ActionLog.resetPasswordOtp.started: email {}", email);
        LocalDateTime now = LocalDateTime.now();
        UsersEntity user = usersRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("userEmail not found"));
        String otpNumber = otpUtil.otpGenerated();
        emailService.sendOtpEmail(email, otpNumber);
        OtpEntity otpEntity = new OtpEntity();
        otpEntity.setOtpDate(now.plusMinutes(2));
        otpEntity.setUserEmail(user.getEmail());
        otpEntity.setOtp(otpNumber);
        otpEntity.setOtpStatus(OtpStatus.ACTIVE);
        otpRepository.save(otpEntity);
        log.info("ActionLog.resetPasswordOtp.started: email {}", email);
        return ResponseEntity.ok("Ok OTP code was sended your email");
    }

    @Transactional()
    public ResponseEntity<String> resetPasswordOtp(String otp, String newPassword, String reNewPassword) {
        log.info("ActionLog.started.resetPasswordOtp: otp {},newPassword {},reNewPassword {}", otp, newPassword, reNewPassword);
        OtpEntity otpEntity = otpRepository.findOtpEntityByOtp(otp).orElseThrow(()->new NotFoundException("this otp could not found"));
        LocalDateTime now = LocalDateTime.now();
        if (otpEntity.getOtpDate().isBefore(now)) {
            otpEntity.setOtpStatus(OtpStatus.INACTIVE);
            otpRepository.save(otpEntity);
        }
        if (otpEntity.getOtpStatus() != OtpStatus.INACTIVE && Objects.equals(otpEntity.getOtp(), otp) && Objects.equals(newPassword, reNewPassword)) {
            UsersEntity user = usersRepository.findByEmail(otpEntity.getUserEmail()).orElseThrow(() -> new NotFoundException("username not found"));
            user.setPassword(passwordEncoder.encode(newPassword));
            usersRepository.save(user);
        }
        otpEntity.setOtpStatus(OtpStatus.INACTIVE);
        otpRepository.save(otpEntity);
        log.info("ActionLog.end.resetPasswordOtp: otp {},newPassword {},reNewPassword {}", otp, newPassword, reNewPassword);
        return null;
    }
}
