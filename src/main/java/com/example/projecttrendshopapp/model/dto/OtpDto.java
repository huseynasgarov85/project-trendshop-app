package com.example.projecttrendshopapp.model.dto;

import com.example.projecttrendshopapp.model.enums.OtpStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OtpDto {
    private Long id;
    private LocalDate otpDate;
    private OtpStatus otpStatus;
    private String otp;
    private String userEmail;
}
