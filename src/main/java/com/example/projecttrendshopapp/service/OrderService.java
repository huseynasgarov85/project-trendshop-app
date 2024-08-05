package com.example.projecttrendshopapp.service;

import com.example.projecttrendshopapp.dao.entity.BasketEntity;
import com.example.projecttrendshopapp.dao.entity.OrderEntity;
import com.example.projecttrendshopapp.dao.entity.UsersEntity;
import com.example.projecttrendshopapp.dao.repository.*;
import com.example.projecttrendshopapp.exception.NotFoundException;
import com.example.projecttrendshopapp.mapper.BasketMapper;
import com.example.projecttrendshopapp.mapper.OrderMapper;
import com.example.projecttrendshopapp.model.dto.OrderDto;
import com.example.projecttrendshopapp.model.enums.Products;
import com.example.projecttrendshopapp.model.enums.Status;
import com.example.projecttrendshopapp.util.ValidationUtil;
import io.github.benas.randombeans.EnhancedRandomBuilder;
import io.github.benas.randombeans.api.EnhancedRandom;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;

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
    private final UsersRepository usersRepository;

    @Transactional
    public List<OrderDto> getAllInformation() {
        log.info("ActionLog.getAllInformation.started.");
        var orderEntities = orderRepository.findAll();
        var orderDtoList = orderEntities.stream().map(this::getOrderDto).toList();
        log.info("ActionLog.getAllInformation.end.");
        return orderDtoList;
    }

    public void createOrder(Long userId, Long orderId) {
        validationUtil.checkStockSize(orderId);
        log.info("ActionLog.createOrder.started: userId {}", userId);
        var baskets = basketRepository.findByStatusAndUserId(Status.SELECTED, userId);
        generalStock(baskets);
        var orderEntity = OrderEntity.builder()
                .users(baskets.get(0).getUser())
                .baskets(baskets)
                .createdAt(LocalDate.now())
                .build();
        baskets.forEach(it -> it.setOrder(orderEntity));
        orderRepository.save(orderEntity);
        log.info("ActionLog.createOrder.end: userId {}", userId);
    }

    @Transactional
    public OrderDto getById(Long orderId) {
        log.info("ActionLog.getById.started: orderId {}", orderId);
        OrderEntity order = orderRepository.findById(orderId).orElseThrow(() -> new NotFoundException("order not found"));
        var orderDto = getOrderDto(order);
        log.info("ActionLog.getById.started: orderId {}", orderId);
        return orderDto;
    }

    private OrderDto getOrderDto(OrderEntity order) {
        List<Object> products = new ArrayList<>();
        for (BasketEntity basketEntity : order.getBaskets()) {
            if (basketEntity.getStatus().equals(Status.SELECTED)) {
                if (basketEntity.getProductName().equals(Products.SHIRT)) {
                    var product = shirtsRepository.findById(basketEntity.getProductId()).orElseThrow(() -> new NotFoundException("basketEntity.getProductId() not found"));
                    products.add(basketMapper.mapShirtEntityToDto(product, basketEntity.getId()));
                } else if (basketEntity.getProductName().equals(Products.TROUSERS)) {
                    var product = trousersRepository.findById(basketEntity.getProductId()).orElseThrow(() -> new NotFoundException("basketEntity.getProductId() not found"));
                    products.add(basketMapper.mapTrousersEntityToDto(product, basketEntity.getId()));
                } else if (basketEntity.getProductName().equals(Products.SHOES)) {
                    var product = shoesRepository.findById(basketEntity.getProductId()).orElseThrow(() -> new NotFoundException("basketEntity.getProductId() not found"));
                    products.add(basketMapper.mapShoesEntityToDto(product, basketEntity.getId()));
                } else if (basketEntity.getProductName().equals(Products.ELECTRICAL_EQUIPMENTS)) {
                    var product = electricalEquipmentRepository.findById(basketEntity.getProductId()).orElseThrow(() -> new NotFoundException("basketEntity.getProductId() not found"));
                    products.add(basketMapper.mapElectricalEntityToDto(product, basketEntity.getId()));
                }
            }
        }
        OrderDto orderDto = orderMapper.mapToDto(order);
        orderDto.setBaskets(products);

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
                var shoesEntity = shoesRepository.findById(basketEntity.getProductId()).orElseThrow(() -> new NotFoundException("basketEntity.getProductId()"));
                shoesEntity.setCount(shoesEntity.getCount() - 1);
            } else if (basketEntity.getProductName().equals(Products.SHIRT)) {
                var shirtEntity = shirtsRepository.findById(basketEntity.getProductId()).orElseThrow(() -> new NotFoundException("basketEntity.getProductId()"));
                shirtEntity.setCounter(shirtEntity.getCounter() - 1);
            } else if (basketEntity.getProductName().equals(Products.TROUSERS)) {
                var trousersEntity = trousersRepository.findById(basketEntity.getProductId()).orElseThrow(() -> new NotFoundException("basketEntity.getProductId()"));
                trousersEntity.setCounter(trousersEntity.getCounter() - 1);
            } else if (basketEntity.getProductName().equals(Products.ELECTRICAL_EQUIPMENTS)) {
                var electricalEquipmentsEntity = electricalEquipmentRepository.findById(basketEntity.getProductId()).orElseThrow(() -> new NotFoundException("basketEntity.getProductId()"));
                electricalEquipmentsEntity.setCount(electricalEquipmentsEntity.getCount() - 1);
            }
        }
    }

    public List<UsersEntity> generateCashBack() {
        log.info("ActionLog.getAll.started.");
        List<OrderEntity> orders = orderRepository.findOrderEntitiesByCreatedAtAfter(LocalDate.now().minusMonths(1));
        Double counter = 0.0;
        Set<Long> userIds = new HashSet<>();
        List<UsersEntity> usersEntityList = new ArrayList<>();
        for (OrderEntity orderEntity : orders) {
            Long userId = orderEntity.getUsers().getId();
            userIds.add(userId);
        }
        for (Long id : userIds) {
            List<OrderEntity> usersOrder = orders.stream().filter(it -> Objects.equals(it.getUsers().getId(), id)).toList();
            for (OrderEntity order : usersOrder) {
                counter += order.getProductsPrice();
                if (order.getProductsPrice() > 150){
                    order.setFreeDelivery(Boolean.TRUE);
                }
            }
            if (counter > 500) {
                UsersEntity usersEntity = usersRepository.findById(id).orElseThrow(() -> new NotFoundException("userId not found"));
                usersEntity.setBalance(usersEntity.getBalance() + (counter * 5) / 100);
                usersRepository.save(usersEntity);
                usersEntityList.add(usersEntity);

            }
        }
        log.info("ActionLog.getAll.end");
        return usersEntityList;
    }

}