package com.example.projecttrendshopapp.model.dto;
import com.example.projecttrendshopapp.model.enums.ProductCategoryElectricalEquipments;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ElectricalEquipmentsDto {
    private Long id;
    private ProductCategoryElectricalEquipments productCategoryElectricalEquipments;
    private String marka;
    private String name;
    private Double price;
}
