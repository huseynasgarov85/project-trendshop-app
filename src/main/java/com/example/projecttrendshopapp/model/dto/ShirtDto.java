package com.example.projecttrendshopapp.model.dto;
import com.example.projecttrendshopapp.model.enums.ProductCategoryShirt;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShirtDto {
    private Long id;
    private ProductCategoryShirt productCategoryShirt;
    private String size;
    private String colour;
    private String marka;
    private Double balance;
}
