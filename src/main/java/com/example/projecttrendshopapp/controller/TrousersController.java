package com.example.projecttrendshopapp.controller;
import com.example.projecttrendshopapp.model.dto.TrousersDto;
import com.example.projecttrendshopapp.service.TrousersService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("trousers")
@RequiredArgsConstructor
public class TrousersController {
    private final TrousersService trousersService;

    @GetMapping()
    public List<TrousersDto> getAll(){
        return trousersService.getAll();
    }
    @GetMapping("/{trouserId}")
    public TrousersDto getById(@PathVariable Long trouserId){
       return trousersService.getById(trouserId);
    }
    @PostMapping()
    public void addTrouser(@RequestBody TrousersDto trousersDto){
        trousersService.addTrouser(trousersDto);
    }
    @PutMapping("/{trouserId}")
    public void updateTrouser(@RequestBody TrousersDto trousersDto,@PathVariable Long trouserId){
        trousersService.updateTrouser(trousersDto,trouserId);
    }
    @DeleteMapping("/{trouserId}")
    public void deleteTrouser(@PathVariable Long trouserId){
        trousersService.deleteTrouser(trouserId);
    }
}
