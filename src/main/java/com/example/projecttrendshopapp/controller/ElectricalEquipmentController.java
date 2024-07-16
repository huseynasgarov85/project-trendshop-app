package com.example.projecttrendshopapp.controller;

import com.example.projecttrendshopapp.model.dto.ElectricalEquipmentsDto;
import com.example.projecttrendshopapp.model.dto.ShirtDto;
import com.example.projecttrendshopapp.service.ElectricalEquipmentService;
import com.example.projecttrendshopapp.service.ShirtsService;
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
    @PostMapping()
    public void  addElectricalEquipment(@RequestBody @Valid ElectricalEquipmentsDto electricalEquipmentsDto){
        electricalEquipmentService.addElectricalEquipment(electricalEquipmentsDto);
    }
    @PutMapping("/{electricalEquipmentId}")
    public void updateELectricalEquipment(@RequestBody @Valid ElectricalEquipmentsDto electricalEquipmentsDto,@PathVariable Long electricalEquipmentId){
        electricalEquipmentService.updateELectricalEquipment(electricalEquipmentsDto,electricalEquipmentId);
    }
    @DeleteMapping("/{electricalEquipmentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeElectricalEqipment(@PathVariable Long electricalEquipmentId){
        electricalEquipmentService.removeElectricalEqipment(electricalEquipmentId);
    }
}
