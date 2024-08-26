package com.example.projecttrendshopapp.dao.repository;

import com.example.projecttrendshopapp.dao.entity.TrousersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TrousersRepository extends JpaRepository<TrousersEntity, Long>, JpaSpecificationExecutor<TrousersEntity> {
}
