package com.example.projecttrendshopapp.controller;

import com.example.projecttrendshopapp.model.dto.ShirtDto;
import com.example.projecttrendshopapp.model.dto.ShirtFilterDto;
import com.example.projecttrendshopapp.service.services.ShirtsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("/shirts")
@RequiredArgsConstructor
public class ShirtsController {
    private final ShirtsService shirtsService;

    @GetMapping
    public Page<ShirtDto> getAll( @PageableDefault(size = 2) Pageable pageable, ShirtFilterDto shirtFilterDto) {
        return shirtsService.getAll(shirtFilterDto, pageable);
    }

    @GetMapping("/{shirtId}")
    public ShirtDto getById(@PathVariable Long shirtId) {
        return shirtsService.getById(shirtId);
    }

    @PostMapping
    public void add(@RequestBody @Valid ShirtDto shirtDto) {
        shirtsService.add(shirtDto);
    }

    @PutMapping("/{shirtId}")
    public void update(@RequestBody @Valid ShirtDto shirtDto, @PathVariable Long shirtId) {
        shirtsService.update(shirtDto, shirtId);
    }

    @DeleteMapping("/{shirtId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long shirtId) {
        shirtsService.remove(shirtId);
    }
}
