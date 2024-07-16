package com.example.projecttrendshopapp.service;

import com.example.projecttrendshopapp.dao.repository.ElectricalEquipmentRepository;
import com.example.projecttrendshopapp.exception.NotFoundException;
import com.example.projecttrendshopapp.mapper.ElecrtricalEquipmentMapper;
import com.example.projecttrendshopapp.model.dto.ElectricalEquipmentsDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ElectricalEquipmentService {
    private final ElectricalEquipmentRepository electricalEquipmentRepository;
    private final ElecrtricalEquipmentMapper elecrtricalEquipmentMapper;


    public List<ElectricalEquipmentsDto> getAll() {
        log.info("ActionLog.getAll.started");
        var electricalEquipmentsEntities = electricalEquipmentRepository.findAll();
        var electricalEquipmentsDtos = electricalEquipmentsEntities.stream().map(elecrtricalEquipmentMapper::mapToDto).toList();
        log.info("ActionLog.getAll.started");
        return electricalEquipmentsDtos;
    }

    public ElectricalEquipmentsDto getById(Long electricalEquipmentId) {
        log.info("ActionLog.getById.started:electricalEquipmentId {}", electricalEquipmentId);
        var electricalEquipmentsEntity = electricalEquipmentRepository.findById(electricalEquipmentId).orElseThrow(() -> new NotFoundException("electricalEquipment not found"));
        var electricalEquipmentsDto = elecrtricalEquipmentMapper.mapToDto(electricalEquipmentsEntity);
        log.info("ActionLog.getById.end:electricalEquipmentId {}", electricalEquipmentId);
        return electricalEquipmentsDto;
    }

    public void addElectricalEquipment(ElectricalEquipmentsDto electricalEquipmentsDto) {
        log.info("ActionLog.addShirts.started:electricalEquipmentsDto {}", electricalEquipmentsDto);
        var electricalEquipmentsEntity = elecrtricalEquipmentMapper.mapToEntity(electricalEquipmentsDto);
        electricalEquipmentRepository.save(electricalEquipmentsEntity);
        log.info("ActionLog.addShirts.end:electricalEquipmentsDto {}", electricalEquipmentsDto);
    }

    public void updateELectricalEquipment(ElectricalEquipmentsDto electricalEquipmentsDto, Long electricalEquipmentId) {
        log.info("ActionLog.updateShirts.started:electricalEquipmentId {}", electricalEquipmentId);
        var electricalEquipmentsEntity = electricalEquipmentRepository.findById(electricalEquipmentId).orElseThrow(() ->new NotFoundException("ElectricalEquipment not found"));
        var updateElectricalEquipment = elecrtricalEquipmentMapper.mapToEntity(electricalEquipmentsEntity, electricalEquipmentsDto);
        electricalEquipmentRepository.save(updateElectricalEquipment);
        log.info("ActionLog.updateShirts.end:electricalEquipmentId {}", electricalEquipmentId);
    }

    public void removeElectricalEqipment(Long electricalEquipmentId) {
        log.info("ActionLog.removeShirt.started:electricalEquipmentId {}", electricalEquipmentId);
        electricalEquipmentRepository.deleteById(electricalEquipmentId);
        log.info("ActionLog.removeShirt.end:electricalEquipmentId {}", electricalEquipmentId);
    }
}
