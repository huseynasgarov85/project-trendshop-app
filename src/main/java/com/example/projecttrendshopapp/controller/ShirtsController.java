package com.example.projecttrendshopapp.controller;

import com.example.projecttrendshopapp.model.dto.ShirtDto;
import com.example.projecttrendshopapp.service.ShirtsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("shirts")
@RequiredArgsConstructor
public class ShirtsController {
    private final ShirtsService shirtsService;

    @GetMapping
    public List<ShirtDto> getAll(){
       return shirtsService.getAll();
    }
    @GetMapping("/{shirtId}")
    public ShirtDto getById(@PathVariable Long shirtId){
        return shirtsService.getById(shirtId);
    }
    @PostMapping()
    public void addShirt(@RequestBody @Valid ShirtDto shirtDto){
        shirtsService.addShirt(shirtDto);
    }
    @PutMapping("/{shirtId}")
    public void updateShirt(@RequestBody @Valid ShirtDto shirtDto,@PathVariable Long shirtId){
        shirtsService.updateShirt(shirtDto,shirtId);
    }
    @DeleteMapping("/{shirtId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeShirt(@PathVariable Long shirtId){
        shirtsService.removeShirt(shirtId);
    }
}
