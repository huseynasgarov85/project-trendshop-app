package com.example.projecttrendshopapp.dao.repository;
import com.example.projecttrendshopapp.dao.entity.ShoesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ShoesRepository extends JpaRepository<ShoesEntity,Long>, JpaSpecificationExecutor<ShoesEntity> {
}
