package com.example.projecttrendshopapp.dao.repository;

import com.example.projecttrendshopapp.dao.entity.UsersEntity;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<UsersEntity,Long> {
    Optional<UsersEntity> findByEmailOrUsername(String email,String userName);
    Optional<UsersEntity> findByUsername(String name);
    Optional<UsersEntity> findByEmail(String email);
}
