package com.example.projecttrendshopapp.dao.repository;

import com.example.projecttrendshopapp.dao.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;


public interface OrderRepository extends JpaRepository<OrderEntity,Long> {
    List<OrderEntity> findOrderEntitiesByCreatedAtAfter(LocalDate date);
}
