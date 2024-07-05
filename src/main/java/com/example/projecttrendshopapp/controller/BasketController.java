package com.example.projecttrendshopapp.controller;

import com.example.projecttrendshopapp.model.dto.BasketDto;
import com.example.projecttrendshopapp.service.BasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("basket")
@RequiredArgsConstructor
public class BasketController {
    private final BasketService basketService;

    @GetMapping("")
    public List<BasketDto> getAllBaskets(){
        return basketService.getAllBaskets();
    }

    @GetMapping("/{basketId}/user/{userId}/product/{productId}")
   public List<BasketDto> getById(@PathVariable Long basketId,@PathVariable Long userId,@PathVariable Long productId){
        return basketService.getById(basketId,userId,productId);
    }
    @GetMapping("/{basketId}")
    public BasketDto getById(@PathVariable Long basketId){
        return basketService.getById(basketId);
    }

    @PostMapping()
    public void addBasket(@RequestBody BasketDto basketDto){
        basketService.addBasket(basketDto);
    }
    @PutMapping("/{basketId}/user/{userId}")
    public void updateBasket(@PathVariable Long basketId,@PathVariable Long userId,@RequestBody BasketDto basketDto){
        basketService.updateBasket(basketId,userId,basketDto);
    }
    @DeleteMapping("/{basketId}")
    public void removeBasket(@PathVariable Long basketId){
        basketService.removeBasket(basketId);
    }

}
