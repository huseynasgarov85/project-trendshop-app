package com.example.projecttrendshopapp.dao.entity;
import com.example.projecttrendshopapp.model.enums.ProductCategoryTrousers;
import jakarta.persistence.*;
import lombok.*;
@Entity
@Table(name = "trousers")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TrousersEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private ProductCategoryTrousers productCategoryTrousers;
    private String colour;
    private String marca;
    private Integer size;
    private Double price;
    private Long counter;
}
