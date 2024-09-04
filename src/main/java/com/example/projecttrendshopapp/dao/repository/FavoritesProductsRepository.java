package com.example.projecttrendshopapp.dao.repository;

import com.example.projecttrendshopapp.dao.entity.FavoritesProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface FavoritesProductsRepository extends JpaRepository<FavoritesProductEntity, Long> , JpaSpecificationExecutor<FavoritesProductEntity> {
}
