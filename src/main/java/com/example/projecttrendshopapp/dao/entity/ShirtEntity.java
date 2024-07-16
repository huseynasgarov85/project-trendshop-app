package com.example.projecttrendshopapp.dao.entity;
import com.example.projecttrendshopapp.model.enums.ProductCategoryShirt;
import jakarta.persistence.*;
import lombok.*;
@Entity
@Table(name = "shirt")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ShirtEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private ProductCategoryShirt productCategoryShirt;
    private String size;
    private String colour;
    private String marka;
    private Double balance;
    private Long counter;
}
