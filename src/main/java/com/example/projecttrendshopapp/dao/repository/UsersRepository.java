package com.example.projecttrendshopapp.dao.repository;

import com.example.projecttrendshopapp.dao.entity.UsersEntity;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<UsersEntity, Long> {

    @Query("SELECT u FROM UsersEntity u WHERE u.email = :email OR u.username = :username")
    Optional<UsersEntity> findByEmailOrUsername(@Param("email") String email, @Param("username") String username);

    @Query("select u from UsersEntity u where u.username = :name ")
    Optional<UsersEntity> findByUsername(@Param("name") String name);

    @Query(value = "SELECT * FROM users WHERE email = :email", nativeQuery = true)
    Optional<UsersEntity> findByEmail(@Param("email") String email);
}
