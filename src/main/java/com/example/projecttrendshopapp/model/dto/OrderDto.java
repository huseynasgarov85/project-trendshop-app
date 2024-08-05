package com.example.projecttrendshopapp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
   private List<Object> baskets;
   private Long userId;
   private Long id;
   private Double productsPrice;
   private LocalDate createdAt;
}
