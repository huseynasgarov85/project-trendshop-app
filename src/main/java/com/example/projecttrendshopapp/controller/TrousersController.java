package com.example.projecttrendshopapp.controller;

import com.example.projecttrendshopapp.dto.TrousersDto;
import com.example.projecttrendshopapp.dto.TrousersFilterDto;
import com.example.projecttrendshopapp.service.serviceImpl.trousers.TrousersService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/trousers")
@RequiredArgsConstructor
public class TrousersController {
    private final TrousersService trousersService;

    @GetMapping
    public Page<TrousersDto> getAll(TrousersFilterDto trousersFilterDto, @PageableDefault(size = 2) Pageable pageable) {
        return trousersService.getAll(trousersFilterDto, pageable);
    }

    @GetMapping("/{trouserId}")
    public TrousersDto getById(@PathVariable Long trouserId) {
        return trousersService.getById(trouserId);
    }

    @PostMapping
    public void add(@RequestBody @Valid TrousersDto trousersDto) {
        trousersService.add(trousersDto);
    }

    @PutMapping("/{trouserId}")
    public void updater(@RequestBody @Valid TrousersDto trousersDto, @PathVariable Long trouserId) {
        trousersService.update(trousersDto, trouserId);
    }

    @DeleteMapping("/{trouserId}")
    public void delete(@PathVariable Long trouserId) {
        trousersService.delete(trouserId);
    }
}
