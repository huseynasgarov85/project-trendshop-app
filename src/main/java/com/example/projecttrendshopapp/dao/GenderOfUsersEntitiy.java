package com.example.projecttrendshopapp.dao;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "genderusers")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class GenderUsersEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String man;
    private String woman;
}
