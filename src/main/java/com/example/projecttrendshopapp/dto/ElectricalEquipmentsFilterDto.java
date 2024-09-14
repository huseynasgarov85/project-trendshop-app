package com.example.projecttrendshopapp.dto;

import com.example.projecttrendshopapp.enums.ProductCategoryElectricalEquipments;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ElectricalEquipmentsFilterDto {
    private Long id;
    @NotNull
    private ProductCategoryElectricalEquipments productCategoryElectricalEquipments;
    @NotBlank
    private String marca;
    @NotNull
    @PositiveOrZero
    private Double price;
    @NotNull
    private Long count;
}
