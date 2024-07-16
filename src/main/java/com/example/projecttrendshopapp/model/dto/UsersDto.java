package com.example.projecttrendshopapp.model.dto;

import com.example.projecttrendshopapp.model.enums.GenderCategory;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsersDto {
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String surname;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String countryCode;
    @NotBlank
    private String phoneNumber;
    @NotNull
    private GenderCategory genderCategory;
    @NotBlank
    @Size(min = 6, max = 10)
    private String password;
    @NotNull
    @PositiveOrZero
    private Double balance;
    private List<CardsDto> cards;
}
