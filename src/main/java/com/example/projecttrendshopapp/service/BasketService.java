package com.example.projecttrendshopapp.service;

import com.example.projecttrendshopapp.dao.repository.*;
import com.example.projecttrendshopapp.exception.NotFoundException;
import com.example.projecttrendshopapp.mapper.BasketMapper;
import com.example.projecttrendshopapp.model.dto.BasketDto;
import com.example.projecttrendshopapp.model.dto.BasketWithProductsDto;
import com.example.projecttrendshopapp.model.enums.Products;
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
    private final ShirtsRepository shirtsRepository;
    private final ShoesRepository shoesRepository;
    private final TrousersRepository trousersRepository;
    private final ElectricalEquipmentRepository electricalEquipmentRepository;

    public List<BasketDto> getAllBaskets() {
        log.info("ActionLog.getAllBaskets.started");
        var basketEntity = basketRepository.findAll();
        var basketDto = basketEntity.stream().map(basketMapper::mapToDto).toList();
        log.info("ActionLog.getAllBaskets.end");
        return basketDto;
    }

    public BasketWithProductsDto getById(Long basketId) {
        log.info("ActionLog.getById.started: basketId {}", basketId);
        var basketEntity = basketRepository.findById(basketId).orElseThrow(() -> new NotFoundException("basketId not found"));
        Object products = null;
        if (basketEntity.getProductName().equals(Products.SHIRT)) {
            products = shirtsRepository.findById(basketEntity.getProductId())
                    .orElseThrow(() -> new NotFoundException("basketEntity.getProductId() not found"));
        } else if (basketEntity.getProductName().equals(Products.TROUSERS)) {
            products = trousersRepository.findById(basketEntity.getProductId()).orElseThrow(() -> new NotFoundException("basketEntity.getProductId() not found"));
        } else if (basketEntity.getProductName().equals(Products.SHOES)) {
            products = shoesRepository.findById(basketEntity.getProductId()).orElseThrow(() -> new NotFoundException("basketEntity.getProductId() not found"));
        } else if (basketEntity.getProductName().equals(Products.ELECTRICAL_EQUIPMENTS)) {
            products = electricalEquipmentRepository.findById(basketEntity.getProductId()).orElseThrow(() -> new NotFoundException("basketEntity.getProductId() not found"));
        }
        BasketWithProductsDto basketWithProductsDto = basketMapper.mapToDtoV2(basketEntity);
        basketWithProductsDto.setProduct(products);
        log.info("ActionLog.getById.end: basketId {}", basketId);
        return basketWithProductsDto;
    }

    public void addBasket(BasketDto basketDto) {
        log.info("ActionLog.addBasket.started: basketDto {}", basketDto);
        var basketEntity = basketMapper.mapToEntity(basketDto);
        basketRepository.save(basketEntity);
        log.info("ActionLog.addBasket.end: basketDto {}", basketDto);
    }

    public void updateBasket(Long basketId, Long userId, BasketDto basketDto) {
        log.info("ActionLog.updateBasket.started: basketId {},userId {}", basketId, userId);
        var basketEntity = basketRepository.findById(basketId).orElseThrow(() -> new NotFoundException("basketId not found"));
        if (!basketEntity.getUser().getId().equals(userId)) {
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
