package com.example.projecttrendshopapp.dao.repository;

import com.example.projecttrendshopapp.dao.entity.ShirtEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ShirtsRepository extends JpaRepository<ShirtEntity, Long>, JpaSpecificationExecutor<ShirtEntity> {
}
