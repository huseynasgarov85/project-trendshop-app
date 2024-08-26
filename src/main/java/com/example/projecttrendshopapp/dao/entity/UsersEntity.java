package com.example.projecttrendshopapp.dao.entity;

import com.example.projecttrendshopapp.model.enums.GenderCategory;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
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
    private String username;
    private String name;
    private String surname;
    private String email;
    private String countryCode;
    private String phoneNumber;
    private String password;
    @Enumerated(EnumType.STRING)
    private GenderCategory genderCategory;
    private Double balance;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "users", orphanRemoval = true,fetch = FetchType.EAGER)
    @ToString.Exclude
    private List<CardsEntity> cards;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "user",fetch = FetchType.EAGER)
    @JsonBackReference
    @ToString.Exclude
    private List<RoleEntity> roles;
    private LocalDate dateOfCreation;
}
