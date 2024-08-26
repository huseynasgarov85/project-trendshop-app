package com.example.projecttrendshopapp.controller;
import com.example.projecttrendshopapp.model.dto.TrousersDto;
import com.example.projecttrendshopapp.model.dto.TrousersFilterDto;
import com.example.projecttrendshopapp.service.services.TrousersService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trousers")
@RequiredArgsConstructor
public class TrousersController {
    private final TrousersService trousersService;

    @GetMapping
    public List<TrousersDto> getAll(TrousersFilterDto trousersFilterDto){
        return trousersService.getAll(trousersFilterDto);
    }
    @GetMapping("/{trouserId}")
    public TrousersDto getById(@PathVariable Long trouserId){
       return trousersService.getById(trouserId);
    }
    @PostMapping
    public void add(@RequestBody @Valid TrousersDto trousersDto){
        trousersService.add(trousersDto);
    }
    @PutMapping("/{trouserId}")
    public void updater(@RequestBody @Valid TrousersDto trousersDto,@PathVariable Long trouserId){
        trousersService.update(trousersDto,trouserId);
    }
    @DeleteMapping("/{trouserId}")
    public void delete(@PathVariable Long trouserId){
        trousersService.delete(trouserId);
    }
}
