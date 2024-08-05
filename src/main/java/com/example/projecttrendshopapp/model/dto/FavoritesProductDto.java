package com.example.projecttrendshopapp.model.dto;

import com.example.projecttrendshopapp.dao.entity.UsersEntity;
import com.example.projecttrendshopapp.model.enums.Products;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FavoritesProductDto {
    private Long id;
    @NotNull
    private UsersEntity user;
    @NotNull
    private Products productName;
    @NotNull
    private List<Object> products;
}
