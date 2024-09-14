package com.example.projecttrendshopapp.dao.entity;

import com.example.projecttrendshopapp.enums.ProductsRating;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UsersEntity users;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<BasketEntity> baskets;
    @Enumerated(EnumType.STRING)
    private ProductsRating productsRating;
    private Boolean freeDelivery;
    private Double productsPrice;
    private LocalDate createdAt;
}
