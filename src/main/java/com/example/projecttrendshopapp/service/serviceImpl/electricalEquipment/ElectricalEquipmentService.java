package com.example.projecttrendshopapp.service.serviceImpl.electricalEquipment;

import com.example.projecttrendshopapp.dto.ElectricalEquipmentsDto;
import com.example.projecttrendshopapp.dto.ElectricalEquipmentsFilterDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ElectricalEquipmentService {
    Page<ElectricalEquipmentsDto> getAll(ElectricalEquipmentsFilterDto electricalEquipmentsFilterDto, Pageable pageable);

    ElectricalEquipmentsDto getById(Long electricalEquipmentId);

    void add(ElectricalEquipmentsDto electricalEquipmentsDto);

    void update(ElectricalEquipmentsDto electricalEquipmentsDto, Long electricalEquipmentId);

    void remove(Long electricalEquipmentId);
}
