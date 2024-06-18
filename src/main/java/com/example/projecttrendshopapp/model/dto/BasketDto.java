package com.example.projecttrendshopapp.model.dto;

import com.example.projecttrendshopapp.dao.entity.UsersEntity;
import com.example.projecttrendshopapp.model.enums.Products;
import com.example.projecttrendshopapp.model.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BasketDto {
    private Long id;
    private Status status;
    private Products productName;
    private UsersEntity user;
    private String productId;
}
