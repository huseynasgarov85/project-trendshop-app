package com.example.projecttrendshopapp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardsDto {
    private Long id;
    private String pan;
    private String cvv;
    private LocalDate expireDate;
    private Double cardBalance;
}
