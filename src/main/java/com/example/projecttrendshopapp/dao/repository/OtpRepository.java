package com.example.projecttrendshopapp.dao.repository;

import com.example.projecttrendshopapp.dao.entity.OtpEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OtpRepository extends JpaRepository<OtpEntity,Long> {
    Optional<OtpEntity> findByOtpAndUserEmail(String otp,String userEmail);
    Optional<OtpEntity> findOtpEntityByOtp(String otp);
}
