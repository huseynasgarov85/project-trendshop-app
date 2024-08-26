package com.example.projecttrendshopapp.model.dto;

import com.example.projecttrendshopapp.model.enums.ProductCategoryTrousers;
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
public class TrousersDto {
    private Long id;
    @NotNull
    private ProductCategoryTrousers productCategoryTrousers;
    @NotBlank
    private String colour;
    @NotBlank
    private String marca;
    @NotNull
    @Size(min = 28, max = 46)
    private Integer size;
    @NotNull
    @PositiveOrZero
    private Double price;
    @NotNull
    private Long counter;
}
