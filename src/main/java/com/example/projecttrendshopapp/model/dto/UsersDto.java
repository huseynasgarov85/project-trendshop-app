package com.example.projecttrendshopapp.model.dto;
import com.example.projecttrendshopapp.model.enums.GenderCategory;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsersDto {
    private Long id;
    private String name;
    private String surname;
    @Email
    private String email;
    private String countryCode;
    private String phoneNumber;
    private GenderCategory genderCategory;
    private String password;
    private Double balance;
    private List<CardsDto> cards;
}
