package com.example.projecttrendshopapp.controller;

import com.example.projecttrendshopapp.model.dto.OrderDto;
import com.example.projecttrendshopapp.service.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/order")
@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping
    public List<OrderDto> getAll(){
        return orderService.getAll();
    }
    @GetMapping("/{orderId}")
    public OrderDto getById(@PathVariable Long orderId){
        return orderService.getById(orderId);
    }
    @PostMapping("/{userId}/{orderId}")
    public void create(@PathVariable Long userId,@PathVariable Long orderId){
        orderService.create(userId,orderId);
    }
    @DeleteMapping("/{orderId}")
    public void remove(@PathVariable Long orderId){
        orderService.remove(orderId);
    }

}
