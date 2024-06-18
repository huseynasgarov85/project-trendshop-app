package com.example.projecttrendshopapp.dao.repository;

import com.example.projecttrendshopapp.dao.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity,Long> {
}
