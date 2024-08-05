package com.example.projecttrendshopapp.model.dto;

import com.example.projecttrendshopapp.model.enums.ProductCategoryShirt;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShirtDto {
    private Long id;
    @NotNull
    private ProductCategoryShirt productCategoryShirt;
    @NotBlank
    private String size;
    @NotBlank
    private String colour;
    @NotBlank
    private String marka;
    @NotNull
    @PositiveOrZero
    private Double balance;
    @NotNull
    private Long counter;
}
