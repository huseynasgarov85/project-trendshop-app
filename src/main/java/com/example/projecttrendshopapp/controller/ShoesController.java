package com.example.projecttrendshopapp.controller;

import com.example.projecttrendshopapp.model.dto.ShoesDto;
import com.example.projecttrendshopapp.service.ShirtsService;
import com.example.projecttrendshopapp.service.ShoesService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("shoes")
@RequiredArgsConstructor
public class ShoesController {
    private final ShoesService shoesService;

    @GetMapping
    public List<ShoesDto> getAll() {
        return shoesService.getAll();
    }

    @GetMapping("/{shoeId}")
    public ShoesDto getById(@PathVariable Long shoeId) {
        return shoesService.getById(shoeId);
    }

    @PostMapping
    public void addShoe(@RequestBody @Valid ShoesDto shoesDto){
        shoesService.addShoe(shoesDto);
    }

    @PutMapping("/{shoeId}")
    public void updateShoe(@RequestBody @Valid ShoesDto shoesDto,@PathVariable Long shoeId){
        shoesService.updateShoe(shoesDto,shoeId);
    }

    @DeleteMapping("/{shoeId}")
    public void removeShoe(@PathVariable Long shoeId){
        shoesService.removeShoe(shoeId);
    }
}
