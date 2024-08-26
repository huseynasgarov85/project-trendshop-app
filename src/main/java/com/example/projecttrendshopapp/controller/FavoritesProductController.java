package com.example.projecttrendshopapp.controller;

import com.example.projecttrendshopapp.model.dto.FavoritesProductDto;
import com.example.projecttrendshopapp.service.services.FavoritesProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/favorites")
@RequiredArgsConstructor
public class FavoritesProductController {
    private final FavoritesProductService favoritesProductService;

    @GetMapping
    public List<FavoritesProductDto> getAll() {
        return favoritesProductService.getAll();
    }

    @GetMapping("/{favoritesProductId}")
    public FavoritesProductDto getById(@PathVariable Long favoritesProductId) {
        return favoritesProductService.getById(favoritesProductId);
    }

    @PostMapping
    public void create(@RequestBody @Valid FavoritesProductDto favoritesProductDto) {
        favoritesProductService.create(favoritesProductDto);
    }

    @DeleteMapping("/{favoritesProductId}")
    public void remove(@PathVariable Long favoritesProductId) {
        favoritesProductService.remove(favoritesProductId);
    }

}
