package com.example.projecttrendshopapp.dto;

import com.example.projecttrendshopapp.dao.entity.OrderEntity;
import com.example.projecttrendshopapp.enums.Products;
import com.example.projecttrendshopapp.enums.Status;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BasketDto {
    private Long id;
    @NotNull
    private Status status;
    @NotNull
    private Products productName;
    @NotNull
    private UsersDto user;
    @NotNull
    private Long productId;
    @NotNull
    private LocalDate placingOfTheGoodsTime;
    @NotNull
    private Long orderId;
}
