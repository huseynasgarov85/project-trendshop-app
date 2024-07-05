package com.example.projecttrendshopapp.model.dto;

import com.example.projecttrendshopapp.dao.entity.UsersEntity;
import com.example.projecttrendshopapp.model.enums.Products;
import com.example.projecttrendshopapp.model.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BasketDto {
    private Long id;
    private Status status;
    private Products productName;
    private UsersDto user;
    private Long productId;
}
