package com.example.projecttrendshopapp.dao.repository;

import com.example.projecttrendshopapp.dao.entity.ElectricalEquipmentsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ElectricalEquipmentRepository extends JpaRepository<ElectricalEquipmentsEntity,Long>, JpaSpecificationExecutor<ElectricalEquipmentsEntity> {
}
