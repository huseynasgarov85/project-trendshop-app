package com.example.projecttrendshopapp.controller;

import com.example.projecttrendshopapp.model.dto.BasketDto;
import com.example.projecttrendshopapp.model.dto.BasketWithProductsDto;
import com.example.projecttrendshopapp.service.services.BasketService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/basket")
@RequiredArgsConstructor
public class BasketController {
    private final BasketService basketService;

    @GetMapping
    public Page<BasketDto> getAll(@PageableDefault(size = 2) Pageable pageable){
        return basketService.getAll(pageable);
    }

    @GetMapping("/{basketId}")
    public BasketWithProductsDto getById(@PathVariable Long basketId){
        return basketService.getById(basketId);
    }

    @PostMapping
    public void add(@RequestBody @Valid BasketDto basketDto){
        basketService.add(basketDto);
    }

    @PatchMapping("/{basketId}/{userId}")
    public void update(@PathVariable Long basketId,@PathVariable Long userId,@RequestBody @Valid BasketDto basketDto){
        basketService.update(basketId,userId,basketDto);
    }

    @DeleteMapping("/{basketId}")
    public void remove(@PathVariable Long basketId){
        basketService.remove(basketId);
    }

}
