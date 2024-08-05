package com.example.projecttrendshopapp.dao.entity;

import com.example.projecttrendshopapp.model.enums.Products;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "favorites")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FavoritesProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UsersEntity user;
    @Enumerated(EnumType.STRING)
    private Products productName;
    private Long productId;
}
