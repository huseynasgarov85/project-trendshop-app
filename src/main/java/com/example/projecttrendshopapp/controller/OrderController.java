package com.example.projecttrendshopapp.controller;

import com.example.projecttrendshopapp.model.dto.OrderDto;
import com.example.projecttrendshopapp.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("order")
@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping
    public List<OrderDto> getAllInformation(){
        return orderService.getAllInformation();
    }
    @GetMapping("/{orderId}")
    public OrderDto getById(@PathVariable Long orderId){
        return orderService.getById(orderId);
    }
    @PatchMapping("/{userId}/{orderId}")
    public void createOrders(@PathVariable Long userId,@PathVariable Long orderId){
        orderService.createOrder(userId,orderId);
    }
    @DeleteMapping("/{orderId}")
    public void removeOrder(@PathVariable Long orderId){
        orderService.removeOrder(orderId);
    }

}
