package com.example.projecttrendshopapp.dao.repository;

import com.example.projecttrendshopapp.dao.entity.RoleEntity;
import com.example.projecttrendshopapp.dao.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.management.relation.Role;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    Optional<RoleEntity> findRoleEntityByUser_Username(String username);
}
