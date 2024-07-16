package com.example.projecttrendshopapp.service;

import com.example.projecttrendshopapp.dao.entity.BasketEntity;
import com.example.projecttrendshopapp.dao.entity.OrderEntity;
import com.example.projecttrendshopapp.dao.repository.*;
import com.example.projecttrendshopapp.exception.NotFoundException;
import com.example.projecttrendshopapp.mapper.BasketMapper;
import com.example.projecttrendshopapp.mapper.OrderMapper;
import com.example.projecttrendshopapp.model.dto.OrderDto;
import com.example.projecttrendshopapp.model.enums.Products;
import com.example.projecttrendshopapp.model.enums.Status;
import com.example.projecttrendshopapp.util.ValidationUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final BasketRepository basketRepository;
    private final ShirtsRepository shirtsRepository;
    private final ShoesRepository shoesRepository;
    private final TrousersRepository trousersRepository;
    private final ElectricalEquipmentRepository electricalEquipmentRepository;
    private final BasketMapper basketMapper;
    private final ValidationUtil validationUtil;

    @Transactional
    public List<OrderDto> getAllInformation() {
        log.info("ActionLog.getAllInformation.started.");
        var orderEntities = orderRepository.findAll();
        var orderDtoList = orderEntities.stream().map(orderMapper::mapToDto).toList();
        log.info("ActionLog.getAllInformation.end.");
        return orderDtoList;
    }

    public void createOrder(Long userId, Long orderId) {
        validationUtil.checkStockSize(orderId);
        log.info("ActionLog.createOrder.started: userId {}", userId);
        var baskets = basketRepository.findByStatusAndUserId(Status.SELECTED, userId);
        generalStock(baskets);
        var orderEntity = OrderEntity.builder()
                .users(baskets.getFirst().getUser())
                .baskets(baskets)
                .build();
        baskets.forEach(it -> it.setOrder(orderEntity));
        orderRepository.save(orderEntity);
        log.info("ActionLog.createOrder.end: userId {}", userId);
    }

    @Transactional
    public OrderDto getById(Long orderId) {
        log.info("ActionLog.getById.started: orderId {}", orderId);
        OrderEntity order = orderRepository.findById(orderId).orElseThrow(() -> new NotFoundException("order not found"));
        List<Object> products = new ArrayList<>();
        for (BasketEntity basketEntity : order.getBaskets()) {
            if (basketEntity.getStatus().equals(Status.SELECTED)) {
                if (basketEntity.getProductName().equals(Products.SHIRT)) {
                    var product = shirtsRepository.findById(basketEntity.getProductId()).orElseThrow(() -> new NotFoundException("basketEntity.getProductId() not found"));
                    products.add(basketMapper.mapShirtEntityToDto(product));
                } else if (basketEntity.getProductName().equals(Products.TROUSERS)) {
                    var product = trousersRepository.findById(basketEntity.getProductId()).orElseThrow(() -> new NotFoundException("basketEntity.getProductId() not found"));
                    products.add(basketMapper.mapTrousersEntityToDto(product));
                } else if (basketEntity.getProductName().equals(Products.SHOES)) {
                    var product = shoesRepository.findById(basketEntity.getProductId()).orElseThrow(() -> new NotFoundException("basketEntity.getProductId() not found"));
                    products.add(basketMapper.mapShoesEntityToDto(product));
                } else if (basketEntity.getProductName().equals(Products.ELECTRICAL_EQUIPMENTS)) {
                    var product = electricalEquipmentRepository.findById(basketEntity.getProductId()).orElseThrow(() -> new NotFoundException("basketEntity.getProductId() not found"));
                    products.add(basketMapper.mapElectricalEntityToDto(product));
                }
            }
        }
        OrderDto orderDto = orderMapper.mapToDto(order);
        orderDto.setBaskets(products);
        log.info("ActionLog.getById.started: orderId {}", orderId);
        return orderDto;
    }

    public void removeOrder(Long orderId) {
        log.info("ActionLig.removeOrder.started:orderId {}", orderId);
        orderRepository.deleteById(orderId);
        log.info("ActionLig.removeOrder.started:orderId {}", orderId);
    }

    public void generalStock(List<BasketEntity> baskets) {
        for (BasketEntity basketEntity : baskets) {
            if (basketEntity.getProductName().equals(Products.SHOES)) {
                var shoesEntity = shoesRepository.findById(basketEntity.getProductId()).orElseThrow();
                shoesEntity.setCount(shoesEntity.getCount() - 1);
            } else if (basketEntity.getProductName().equals(Products.SHIRT)) {
                var shirtEntity = shirtsRepository.findById(basketEntity.getProductId()).orElseThrow();
                shirtEntity.setCounter(shirtEntity.getCounter() - 1);
            } else if (basketEntity.getProductName().equals(Products.TROUSERS)) {
                var trousersEntity = trousersRepository.findById(basketEntity.getProductId()).orElseThrow();
                trousersEntity.setCounter(trousersEntity.getCounter() - 1);
            } else if (basketEntity.getProductName().equals(Products.ELECTRICAL_EQUIPMENTS)) {
                var electricalEquipmentsEntity = electricalEquipmentRepository.findById(basketEntity.getProductId()).orElseThrow();
                electricalEquipmentsEntity.setCount(electricalEquipmentsEntity.getCount() - 1);
            }
        }
    }

}
