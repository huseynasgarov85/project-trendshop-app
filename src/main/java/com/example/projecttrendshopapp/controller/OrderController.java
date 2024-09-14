package com.example.projecttrendshopapp.controller;

import com.example.projecttrendshopapp.dto.OrderDto;
import com.example.projecttrendshopapp.service.serviceImpl.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/order")
@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping
    public Page<OrderDto> getAll(@PageableDefault(size = 1) Pageable pageable){
        return orderService.getAll(pageable);
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
