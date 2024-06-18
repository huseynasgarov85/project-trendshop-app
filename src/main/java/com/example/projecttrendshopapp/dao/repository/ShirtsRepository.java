package com.example.projecttrendshopapp.dao.repository;

import com.example.projecttrendshopapp.dao.entity.ShirtEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShirtsRepository extends JpaRepository<ShirtEntity, Long> {
}
