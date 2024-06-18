package com.example.projecttrendshopapp.model.dto;
import com.example.projecttrendshopapp.model.enums.ProductCategoryTrousers;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrousersDto {
    private Long id;
    private ProductCategoryTrousers productCategoryTrousers;
    private String colour;
    private String marka;
    private Integer size;
    private Double price;
}
