package com.example.projecttrendshopapp.controller;

import com.example.projecttrendshopapp.model.dto.ShoesDto;
import com.example.projecttrendshopapp.model.dto.ShoesFilterDto;
import com.example.projecttrendshopapp.service.services.ShoesService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shoes")
@RequiredArgsConstructor
public class ShoesController {
    private final ShoesService shoesService;

    @GetMapping
    public List<ShoesDto> getAll(ShoesFilterDto shoesFilterDto) {
        return shoesService.getAll(shoesFilterDto);
    }

    @GetMapping("/{shoeId}")
    public ShoesDto getById(@PathVariable Long shoeId) {
        return shoesService.getById(shoeId);
    }

    @PostMapping
    public void add(@RequestBody @Valid ShoesDto shoesDto){
        shoesService.add(shoesDto);
    }

    @PutMapping("/{shoeId}")
    public void update(@RequestBody @Valid ShoesDto shoesDto,@PathVariable Long shoeId){
        shoesService.update(shoesDto,shoeId);
    }

    @DeleteMapping("/{shoeId}")
    public void remove(@PathVariable Long shoeId){
        shoesService.remove(shoeId);
    }
}
