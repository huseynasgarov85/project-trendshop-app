package com.example.projecttrendshopapp.dao.entity;

import com.example.projecttrendshopapp.enums.Products;
import com.example.projecttrendshopapp.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

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
    private LocalDate placingOfTheGoodsTime;
}
