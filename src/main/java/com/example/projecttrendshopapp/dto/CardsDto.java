package com.example.projecttrendshopapp.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @Pattern(regexp = "^(?:4[0-9]{3}-[0-9]{4}-[0-9]{4}-[0-9]{4}|5[1-5][0-9]{2}-[0-9]{4}-[0-9]{4}-[0-9]{4})$",
            message = "Invalid credit card number")
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
