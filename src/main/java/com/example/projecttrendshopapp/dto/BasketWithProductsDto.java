package com.example.projecttrendshopapp.dto;

import com.example.projecttrendshopapp.enums.Products;
import com.example.projecttrendshopapp.enums.Status;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BasketWithProductsDto {
    private Long id;
    @NotNull
    private Status status;
    @NotNull
    private Products productName;
    @NotNull
    private UsersDto user;
    private Object product;
}
