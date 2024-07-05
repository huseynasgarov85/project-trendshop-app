package com.example.projecttrendshopapp.dao.entity;

import com.example.projecttrendshopapp.model.enums.Products;
import com.example.projecttrendshopapp.model.enums.Status;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "basket")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BasketEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private Products productName;
    @Enumerated(EnumType.STRING)
    private Status status;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UsersEntity user;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderEntity order;
    private Long productId;
}
