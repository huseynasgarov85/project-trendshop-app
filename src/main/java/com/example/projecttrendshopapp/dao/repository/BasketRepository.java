package com.example.projecttrendshopapp.dao.repository;

import com.example.projecttrendshopapp.dao.entity.BasketEntity;
import com.example.projecttrendshopapp.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface BasketRepository extends JpaRepository<BasketEntity,Long> , JpaSpecificationExecutor<BasketEntity> {
    List<BasketEntity> findByStatusAndUserId(Status status,Long userId);

//    @Query("select e.productName from BasketEntity e")
//    List<String> get();
}
