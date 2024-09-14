package com.example.projecttrendshopapp.dao.entity;
import com.example.projecttrendshopapp.enums.ProductCategoryShoes;
import jakarta.persistence.*;
import lombok.*;
@Entity
@Table(name = "shoes")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ShoesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private ProductCategoryShoes productCategoryShoes;
    private String colour;
    private String marca;
    private Integer size;
    private Double balance;
    private String name;
    private Long count;
}
