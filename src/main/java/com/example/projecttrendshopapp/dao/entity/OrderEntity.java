package com.example.projecttrendshopapp.dao.entity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.mapping.Join;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "basket_id")
    private BasketEntity basket;
}
