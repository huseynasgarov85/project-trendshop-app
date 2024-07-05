package com.example.projecttrendshopapp.service;

import com.example.projecttrendshopapp.dao.entity.BasketEntity;
import com.example.projecttrendshopapp.dao.entity.OrderEntity;
import com.example.projecttrendshopapp.dao.repository.BasketRepository;
import com.example.projecttrendshopapp.dao.repository.OrderRepository;
import com.example.projecttrendshopapp.dao.repository.UsersRepository;
import com.example.projecttrendshopapp.mapper.BasketMapper;
import com.example.projecttrendshopapp.mapper.OrderMapper;
import com.example.projecttrendshopapp.model.dto.Basket;
import com.example.projecttrendshopapp.model.dto.OrderDto;
import com.example.projecttrendshopapp.model.enums.Status;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final UsersRepository usersRepository;
    private final BasketRepository basketRepository;
    private final BasketMapper basketMapper;

    public List<OrderDto> getAllInformation() {
        log.info("ActionLog.getAllInformation.started.");
        var orderEntity = orderRepository.findAll();
        var orderDto = orderEntity.stream().map(orderMapper::mapToDto).toList();
        log.info("ActionLog.getAllInformation.end.");
        return orderDto;
    }

    public void createOrder(Long userId) {
        var baskets = basketRepository.findByStatusAndUserId(Status.SELECTED, userId);
        var orderEntity = OrderEntity.builder()
                .users(baskets.getFirst().getUser())
                .baskets(baskets)
                .build();
        baskets.forEach(it -> it.setOrder(orderEntity));
        orderRepository.save(orderEntity);
    }

//    public OrderDto getOrder(Long orderId) {
//        var orderEntity = orderRepository.findById(orderId).orElseThrow();
//        var baskets = orderEntity.getBaskets();
//
//    }
}
