package com.example.projecttrendshopapp.model.dto;

import com.example.projecttrendshopapp.model.enums.ProductCategoryShoes;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShoesFilterDto {
    private Long id;
    @NotNull
    private ProductCategoryShoes productCategoryShoes;
    @NotBlank
    private String colour;
    @NotBlank
    private String marca;
    @NotNull
    @Size(min = 33, max = 45)
    private Integer shoeSize;
    @NotNull
    @PositiveOrZero
    private Double balance;
    @NotBlank
    private String name;
    @NotNull
    private Long count;
}
