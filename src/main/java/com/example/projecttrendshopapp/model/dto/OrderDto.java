package com.example.projecttrendshopapp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
   private List<BasketDto> basketDtos;
   private Long userId;
}
