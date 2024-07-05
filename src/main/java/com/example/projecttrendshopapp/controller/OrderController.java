package com.example.projecttrendshopapp.controller;

import com.example.projecttrendshopapp.model.dto.OrderDto;
import com.example.projecttrendshopapp.service.OrderService;
import jakarta.persistence.criteria.Order;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("order")
@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping("")
    public List<OrderDto> getAllInformation(){
        return orderService.getAllInformation();
    }
//    @GetMapping("/{orderId}")
//    public OrderDto getById(@PathVariable Long orderId){
//        return orderService.getById(orderId);
//    }

//    @PostMapping
//    public void createOrder(@RequestBody OrderDto orderDto){
//        orderService.createOrder(orderDto);
//    }

    @PostMapping("/{userId}")
    public void createOrders(@PathVariable Long userId){
        orderService.createOrder(userId);
    }

}
