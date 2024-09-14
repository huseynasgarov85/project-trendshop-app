package com.example.projecttrendshopapp.dao.entity;

import com.example.projecttrendshopapp.enums.OtpStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "otp")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OtpEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String otp;
    private LocalDateTime otpDate;
    @Enumerated(EnumType.STRING)
    private OtpStatus otpStatus;
    private String userEmail;
}
