package com.example.projecttrendshopapp.dao.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "cards")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CardsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String pan;
    private String cvv;
    private LocalDate expireDate;
    private Double cardBalance;
    @ManyToOne
    @JoinColumn(name = "users_id")
    @JsonBackReference
    private UsersEntity users;
}
