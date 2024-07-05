package com.example.projecttrendshopapp.dao.entity;

import com.example.projecttrendshopapp.model.dto.Basket;
import jakarta.persistence.*;
import lombok.*;

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
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<BasketEntity> baskets;
}
