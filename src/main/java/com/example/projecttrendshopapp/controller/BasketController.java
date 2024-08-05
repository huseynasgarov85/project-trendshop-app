package com.example.projecttrendshopapp.controller;

import com.example.projecttrendshopapp.model.dto.BasketDto;
import com.example.projecttrendshopapp.model.dto.BasketWithProductsDto;
import com.example.projecttrendshopapp.service.BasketService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("basket")
@RequiredArgsConstructor
public class BasketController {
    private final BasketService basketService;

    @GetMapping
    public List<BasketDto> getAllBaskets(){
        return basketService.getAllBaskets();
    }

    @GetMapping("/{basketId}")
    public BasketWithProductsDto getById(@PathVariable Long basketId){
        return basketService.getById(basketId);
    }

    @PostMapping
    public void addBasket(@RequestBody @Valid BasketDto basketDto){
        basketService.addBasket(basketDto);
    }

    @PatchMapping("/{basketId}/user/{userId}")
    public void updateBasket(@PathVariable Long basketId,@PathVariable Long userId,@RequestBody @Valid BasketDto basketDto){
        basketService.updateBasket(basketId,userId,basketDto);
    }

    @DeleteMapping("/{basketId}")
    public void removeBasket(@PathVariable Long basketId){
        basketService.removeBasket(basketId);
    }

}
