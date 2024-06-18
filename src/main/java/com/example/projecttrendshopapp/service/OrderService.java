package com.example.projecttrendshopapp.service;

import com.example.projecttrendshopapp.dao.repository.OrderRepository;
import com.example.projecttrendshopapp.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
}
