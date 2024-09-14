package com.example.projecttrendshopapp.dto;

import com.example.projecttrendshopapp.enums.ProductCategoryShirt;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShirtFilterDto {
    private Long id;
    @NotNull
    private ProductCategoryShirt productCategoryShirt;
    @NotNull
    private String shirtSize;
    @NotBlank
    private String colour;
    @NotBlank
    private String marca;
    @NotNull
    private Double balance;
    @NotNull
    private Long counter;
}