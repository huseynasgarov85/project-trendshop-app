package com.example.projecttrendshopapp.model.dto;

import com.example.projecttrendshopapp.model.enums.ProductCategoryShirt;
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
    private String size;
    @NotBlank
    private String colour;
    @NotBlank
    private String marca;
    @NotNull
    private Double balance;
    @NotNull
    private Long counter;
}
