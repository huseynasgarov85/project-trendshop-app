package com.example.projecttrendshopapp.dao.entity;

import com.example.projecttrendshopapp.model.enums.GenderCategory;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UsersEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String countryCode;
    private String phoneNumber;
    private String password;
    @Enumerated(EnumType.STRING)
    private GenderCategory genderCategory;
    private Double balance;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "users", orphanRemoval = true)
    private List<CardsEntity> cards;
}
