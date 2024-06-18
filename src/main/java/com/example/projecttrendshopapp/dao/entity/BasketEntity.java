package com.example.projecttrendshopapp.dao.entity;

import com.example.projecttrendshopapp.model.dto.Basket;
import com.example.projecttrendshopapp.model.enums.Products;
import com.example.projecttrendshopapp.model.enums.Status;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.NaturalIdCache;
import org.hibernate.annotations.Type;
import org.hibernate.type.SqlTypes;

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
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "PRODUCT_ID",columnDefinition = "jsonb")
    private Basket basket;
}
