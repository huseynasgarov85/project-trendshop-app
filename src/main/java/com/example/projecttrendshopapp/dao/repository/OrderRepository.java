package com.example.projecttrendshopapp.dao.repository;

import com.example.projecttrendshopapp.dao.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.time.LocalDate;
import java.util.List;


public interface OrderRepository extends JpaRepository<OrderEntity,Long>, JpaSpecificationExecutor<OrderEntity> {
    List<OrderEntity> findOrderEntitiesByCreatedAtAfter(LocalDate date);
}
