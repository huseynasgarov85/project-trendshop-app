package com.example.projecttrendshopapp.controller;

import com.example.projecttrendshopapp.model.dto.ElectricalEquipmentsDto;
import com.example.projecttrendshopapp.model.dto.ElectricalEquipmentsFilterDto;
import com.example.projecttrendshopapp.service.services.ElectricalEquipmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/electricalEquipment")
@RequiredArgsConstructor
public class ElectricalEquipmentController {
    private final ElectricalEquipmentService electricalEquipmentService;

    @GetMapping
    public Page<ElectricalEquipmentsDto> getAll(ElectricalEquipmentsFilterDto electricalEquipmentsFilterDto, @PageableDefault(size = 2) Pageable pageable) {
        return electricalEquipmentService.getAll(electricalEquipmentsFilterDto, pageable);
    }

    @GetMapping("/{electricalEquipmentId}")
    public ElectricalEquipmentsDto getById(@PathVariable Long electricalEquipmentId) {
        return electricalEquipmentService.getById(electricalEquipmentId);
    }

    @PostMapping
    public void add(@RequestBody @Valid ElectricalEquipmentsDto electricalEquipmentsDto) {
        electricalEquipmentService.add(electricalEquipmentsDto);
    }

    @PutMapping("/{electricalEquipmentId}")
    public void update(@RequestBody @Valid ElectricalEquipmentsDto electricalEquipmentsDto, @PathVariable Long electricalEquipmentId) {
        electricalEquipmentService.update(electricalEquipmentsDto, electricalEquipmentId);
    }

    @DeleteMapping("/{electricalEquipmentId}")
    public void remove(@PathVariable Long electricalEquipmentId) {
        electricalEquipmentService.remove(electricalEquipmentId);
    }
}
