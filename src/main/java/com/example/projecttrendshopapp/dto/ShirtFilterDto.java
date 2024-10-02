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
    private ProductCategoryShirt productCategoryShirt;
    private String shirtSize;
    private String colour;
    private String marca;
    private Double balance;
    private Long counter;
}
