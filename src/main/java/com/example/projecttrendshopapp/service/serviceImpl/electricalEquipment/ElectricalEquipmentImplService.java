package com.example.projecttrendshopapp.service.serviceImpl.electricalEquipment;

import com.example.projecttrendshopapp.dao.entity.ElectricalEquipmentsEntity;
import com.example.projecttrendshopapp.dao.repository.ElectricalEquipmentRepository;
import com.example.projecttrendshopapp.dto.ElectricalEquipmentsDto;
import com.example.projecttrendshopapp.dto.ElectricalEquipmentsFilterDto;
import com.example.projecttrendshopapp.exception.NotFoundException;
import com.example.projecttrendshopapp.mapper.ElectricalEquipmentMapper;
import com.example.projecttrendshopapp.service.specification.electricalEquipments.CounterSpecification;
import com.example.projecttrendshopapp.service.specification.electricalEquipments.MarcaSpecification;
import com.example.projecttrendshopapp.service.specification.electricalEquipments.PriceSpecification;
import com.example.projecttrendshopapp.service.specification.electricalEquipments.ProductCategory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ElectricalEquipmentImplService implements ElectricalEquipmentService {
    private final ElectricalEquipmentRepository electricalEquipmentRepository;
    private final ElectricalEquipmentMapper electricalEquipmentMapper;


    @Override
    public Page<ElectricalEquipmentsDto> getAll(ElectricalEquipmentsFilterDto electricalEquipmentsFilterDto, Pageable pageable) {
        log.info("ActionLog.getAll.started");
        var specification = Specification.where(new CounterSpecification(electricalEquipmentsFilterDto.getCount()))
                .and(new MarcaSpecification(electricalEquipmentsFilterDto.getMarca()))
                .and(new PriceSpecification(electricalEquipmentsFilterDto.getPrice()))
                .and(new ProductCategory(electricalEquipmentsFilterDto.getProductCategoryElectricalEquipments()));
        Page<ElectricalEquipmentsEntity> electricalEquipmentsEntities = electricalEquipmentRepository.findAll(specification, pageable);
        var electricalEquipmentsDtos = electricalEquipmentsEntities.stream().map(electricalEquipmentMapper::mapToDto).toList();
        log.info("ActionLog.getAll.started");
        return new PageImpl<>(electricalEquipmentsDtos, electricalEquipmentsEntities.getPageable(), electricalEquipmentsEntities.getTotalElements());
    }

    @Override
    public ElectricalEquipmentsDto getById(Long electricalEquipmentId) {
        log.info("ActionLog.getById.started:electricalEquipmentId {}", electricalEquipmentId);
        var electricalEquipmentsEntity = electricalEquipmentRepository.findById(electricalEquipmentId).orElseThrow(() -> new NotFoundException("electricalEquipment not found"));
        var electricalEquipmentsDto = electricalEquipmentMapper.mapToDto(electricalEquipmentsEntity);
        log.info("ActionLog.getById.end:electricalEquipmentId {}", electricalEquipmentId);
        return electricalEquipmentsDto;
    }

    @Override
    public void add(ElectricalEquipmentsDto electricalEquipmentsDto) {
        log.info("ActionLog.add.started:electricalEquipmentsDto {}", electricalEquipmentsDto);
        var electricalEquipmentsEntity = electricalEquipmentMapper.mapToEntity(electricalEquipmentsDto);
        electricalEquipmentRepository.save(electricalEquipmentsEntity);
        log.info("ActionLog.add.end:electricalEquipmentsDto {}", electricalEquipmentsDto);
    }

    @Override
    public void update(ElectricalEquipmentsDto electricalEquipmentsDto, Long electricalEquipmentId) {
        log.info("ActionLog.update.started:electricalEquipmentId {}", electricalEquipmentId);
        var electricalEquipmentsEntity = electricalEquipmentRepository.findById(electricalEquipmentId).orElseThrow(() -> new NotFoundException("ElectricalEquipment not found"));
        var updateElectricalEquipment = electricalEquipmentMapper.mapToEntity(electricalEquipmentsEntity, electricalEquipmentsDto);
        electricalEquipmentRepository.save(updateElectricalEquipment);
        log.info("ActionLog.update.end:electricalEquipmentId {}", electricalEquipmentId);
    }

    @Override
    public void remove(Long electricalEquipmentId) {
        log.info("ActionLog.remove.started:electricalEquipmentId {}", electricalEquipmentId);
        electricalEquipmentRepository.deleteById(electricalEquipmentId);
        log.info("ActionLog.remove.end:electricalEquipmentId {}", electricalEquipmentId);
    }
}
