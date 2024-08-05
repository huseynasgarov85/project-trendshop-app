package com.example.projecttrendshopapp.controller;

import com.example.projecttrendshopapp.model.dto.ElectricalEquipmentsDto;
import com.example.projecttrendshopapp.service.ElectricalEquipmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("electricalEquipment")
@RequiredArgsConstructor
public class ElectricalEquipmentController {
    private final ElectricalEquipmentService electricalEquipmentService;

    @GetMapping
    public List<ElectricalEquipmentsDto> getAll(){
        return electricalEquipmentService.getAll();
    }
    @GetMapping("/{electricalEquipmentId}")
    public ElectricalEquipmentsDto getById(@PathVariable Long electricalEquipmentId){
        return electricalEquipmentService.getById(electricalEquipmentId);
    }
    @PostMapping
    public void  addElectricalEquipment(@RequestBody @Valid ElectricalEquipmentsDto electricalEquipmentsDto){
        electricalEquipmentService.addElectricalEquipment(electricalEquipmentsDto);
    }
    @PutMapping("/{electricalEquipmentId}")
    public void updateElectricalEquipment(@RequestBody @Valid ElectricalEquipmentsDto electricalEquipmentsDto, @PathVariable Long electricalEquipmentId){
        electricalEquipmentService.updateELectricalEquipment(electricalEquipmentsDto,electricalEquipmentId);
    }
    @DeleteMapping("/{electricalEquipmentId}")
    public void removeElectricalEquipment(@PathVariable Long electricalEquipmentId){
        electricalEquipmentService.removeElectricalEqipment(electricalEquipmentId);
    }
}
