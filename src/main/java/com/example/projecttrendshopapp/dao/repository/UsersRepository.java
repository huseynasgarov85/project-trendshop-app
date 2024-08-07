package com.example.projecttrendshopapp.dao.repository;

import com.example.projecttrendshopapp.dao.entity.UsersEntity;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<UsersEntity,Long> {
    Optional<UsersEntity> findByEmail(String email);
    Optional<UsersEntity> findByName(String name);
}
