package com.example.projecttrendshopapp.controller;

import com.example.projecttrendshopapp.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("order")
@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
}
