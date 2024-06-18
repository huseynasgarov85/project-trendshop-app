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
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_shirt",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "shirt_id"))
    private List<ShirtEntity> shirt;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_shoes",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "shoes_id"))
    private List<ShoesEntity> shoes;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_trousers",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "trouser_id"))
    private List<TrousersEntity> trousers;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_electrical_equipments",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "electrical_equipmment_id"))
    private List<ElectricalEquipmentsEntity> electricalEquipments;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "users", orphanRemoval = true)
    private List<CardsEntity> cards;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<BasketEntity> basketEntities;
}
