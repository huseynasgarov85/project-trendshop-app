package com.example.projecttrendshopapp.model.dto;
import com.example.projecttrendshopapp.model.enums.ProductCategoryShoes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShoesDto {
    private Long id;
    private ProductCategoryShoes productCategoryShoes;
    private String colour;
    private String marka;
    private Integer size;
    private Double balance;
    private String name;
}
