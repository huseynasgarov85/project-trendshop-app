package com.example.projecttrendshopapp.dao.repository;

import com.example.projecttrendshopapp.dao.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    Optional<RoleEntity> findRoleEntityByUser_Username(String username);
}
