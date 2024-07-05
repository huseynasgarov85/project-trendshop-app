package com.example.projecttrendshopapp.service;

import com.example.projecttrendshopapp.dao.repository.*;
import com.example.projecttrendshopapp.exception.NotFoundException;
import com.example.projecttrendshopapp.mapper.BasketMapper;
import com.example.projecttrendshopapp.mapper.UsersMapper;
import com.example.projecttrendshopapp.model.dto.BasketDto;
import com.example.projecttrendshopapp.model.enums.Products;
import com.example.projecttrendshopapp.model.enums.Status;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
public class BasketService {
    private final BasketRepository basketRepository;
    private final BasketMapper basketMapper;
    private final UsersRepository usersRepository;
    private final ShirtsRepository shirtsRepository;
    private final ShoesRepository shoesRepository;
    private final TrousersRepository trousersRepository;
    private final ElectricalEquipmentRepository electricalEquipmentRepository;
    private final UsersMapper usersMapper;


    public List<BasketDto> getAllBaskets() {
        log.info("ActionLog.getAllBaskets.started");
        var basketEntity = basketRepository.findAll();
        var basketDto = basketEntity.stream().map(basketMapper::mapToDto).toList();
        log.info("ActionLog.getAllBaskets.end");
        return basketDto;
    }

    public BasketDto getById(Long basketId) {
        log.info("ActionLog.getById.started: basketId {}", basketId);
        var basketEntity = basketRepository.findById(basketId).orElseThrow(() -> {
            log.error("Action is failed : basketId {}", basketId);
            return new NotFoundException("basketId not found");
        });
        Object products = null;
        if (basketEntity.getProductName().equals(Products.SHIRT)) {
            products = shirtsRepository.findById(basketEntity.getProductId()).orElseThrow(() -> {
                log.error("Action is failed productId : {}", basketEntity.getProductId());
                return new NotFoundException("basketEntity.getProductId() not found");
            });
        } else if (basketEntity.getProductName().equals(Products.TROUSERS)) {
            products = trousersRepository.findById(basketEntity.getProductId()).orElseThrow(() -> {
                log.error("Action is failed productId : {}", basketEntity.getProductId());
                return new NotFoundException("basketEntity.getProductId() not found");
            });
        } else if (basketEntity.getProductName().equals(Products.SHOES)) {
            products = shoesRepository.findById(basketEntity.getProductId()).orElseThrow(() -> {
                log.error("Action is failed productId : {}", basketEntity.getProductId());
                return new NotFoundException("basketEntity.getProductId() not found");
            });
        } else if (basketEntity.getProductName().equals(Products.ELECTRICAL_EQUIPMENTS)) {
            products = electricalEquipmentRepository.findById(basketEntity.getProductId()).orElseThrow(()->{
                log.error("Action is failed productId : {}", basketEntity.getProductId());
                return new NotFoundException("basketEntity.getProductId() not found");
            });
        }
        var basketDto = basketMapper.mapToDto(basketEntity,products);
        log.info("ActionLog.getById.end: basketId {}", basketId);
        return basketDto;
    }

    public List<BasketDto> getById(Long basketId, Long userId, Long productId) {
        log.info("ActionLog.getById.started:basketId {}", basketId);
        var basketEntity = basketRepository.findByUser_IdAndProductIdAndAndStatus(userId, productId, Status.SELECTED);
        List<BasketDto> basketEntitys = basketEntity.stream().map(basketEntity1 -> {
            var basketDto = basketMapper.mapToDto(basketEntity1);
            var user = usersRepository.findById(userId).orElseThrow(() -> {
                log.error("Action is failed userId : {}", userId);
                return new NotFoundException("userId not found");
            });
            var userDto = usersMapper.mapToDto(user);
            basketDto.setUser(userDto);
            if (basketDto.getProductName().equals(Products.SHIRT)) {
                var shirt = shirtsRepository.findById(productId).orElseThrow(() -> {
                    log.error("Action is failed productId : {}", productId);
                    return new NotFoundException("productId not found");
                });
                basketDto.setProductId(shirt.getId());
            } else if (basketDto.getProductName().equals(Products.SHOES)) {
                var shoes = shoesRepository.findById(productId).orElseThrow(() -> {
                    log.error("Action is failed productId : {}", productId);
                    return new NotFoundException("productId not found");
                });
                basketDto.setProductId(shoes.getId());
            } else if (basketDto.getProductName().equals(Products.TROUSERS)) {
                var trousers = trousersRepository.findById(productId).orElseThrow(() -> {
                    log.error("Action is failed productId : {}", productId);
                    return new NotFoundException("productId not found");
                });
                basketDto.setProductId(trousers.getId());
            } else {
                var electricalEquipment = electricalEquipmentRepository.findById(productId).orElseThrow(() -> {
                    log.error("Action is failed productId : {}", productId);
                    return new NotFoundException("productId not found");
                });
                basketDto.setProductId(electricalEquipment.getId());
            }
            return basketDto;
        }).toList();
        log.info("ActionLog.getById.started:basketId {}", basketId);
        return basketEntitys;
    }

    public void addBasket(BasketDto basketDto) {
        log.info("ActionLog.addBasket.started: basketDto {}", basketDto);
        var basketEntity = basketMapper.mapToEntity(basketDto);
        basketRepository.save(basketEntity);
        log.info("ActionLog.addBasket.end: basketDto {}", basketDto);
    }

    public void updateBasket(Long basketId, Long userId, BasketDto basketDto) {
        log.info("ActionLog.updateBasket.started: basketId {},userId {}", basketId, userId);
        var basketEntity = basketRepository.findById(basketId).orElseThrow(() -> {
            log.error("Action is failed basketId : {}", basketId);
            return new NotFoundException("basketId not found");
        });
        if (!basketEntity.getUser().getId().equals(userId)) {
            log.error("Action is failed userId");
            throw new NotFoundException("user not found");
        }
        var updateBasket = basketMapper.mapToEntity(basketEntity, basketDto);
        basketRepository.save(updateBasket);
        log.info("ActionLog.updateBasket.started: basketId {},userId {}", basketId, userId);
    }

    public void removeBasket(Long basketId) {
        log.info("ActionLog.removeBasket.started: basketId {}", basketId);
        basketRepository.deleteById(basketId);
        log.info("ActionLog.removeBasket.end: basketId {}", basketId);
    }
}
