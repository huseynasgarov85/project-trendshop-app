package com.example.projecttrendshopapp.dao.entity;
import com.example.projecttrendshopapp.enums.ProductCategoryElectricalEquipments;
import jakarta.persistence.*;
import lombok.*;
@Entity
@Table(name = "electrical_equipments")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
@ToString
public class ElectricalEquipmentsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private ProductCategoryElectricalEquipments productCategoryElectricalEquipments;
    private String marca;
    private Long count;
    private Double price;
}
