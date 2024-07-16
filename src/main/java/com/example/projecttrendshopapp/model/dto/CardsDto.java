package com.example.projecttrendshopapp.model.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardsDto {
    private Long id;
    @NotBlank
    @Pattern(regexp ="^(?:4[0-9]{12}(?:[0-9]{3})?|5[1-5][0-9]{14})$",message = "Invalide credit card number")
    private String pan;
    @NotBlank
    @Size(min = 3,max = 3)
    private String cvv;
    @FutureOrPresent
    @NotNull
    private LocalDate expireDate;
    @NotNull
    @PositiveOrZero
    private Double cardBalance;
}
