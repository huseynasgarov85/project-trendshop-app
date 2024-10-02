package com.example.projecttrendshopapp.service.serviceImpl.basket;

import com.example.projecttrendshopapp.dao.entity.*;
import com.example.projecttrendshopapp.dao.repository.*;
import com.example.projecttrendshopapp.dto.BasketDto;
import com.example.projecttrendshopapp.dto.BasketWithProductsDto;
import com.example.projecttrendshopapp.enums.Products;
import com.example.projecttrendshopapp.enums.Status;
import com.example.projecttrendshopapp.exception.NotFoundException;
import com.example.projecttrendshopapp.mapper.BasketMapper;
import com.example.projecttrendshopapp.util.ValidationUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
public class BasketImplService implements BasketService {
    private final BasketRepository basketRepository;
    private final BasketMapper basketMapper;
    private final ShirtsRepository shirtsRepository;
    private final ShoesRepository shoesRepository;
    private final TrousersRepository trousersRepository;
    private final ElectricalEquipmentRepository electricalEquipmentRepository;
    private final OrderRepository orderRepository;
    private final ValidationUtil validationUtil;

    @Override
    public Page<BasketDto> getAll(Pageable pageable) {
        log.info("ActionLog.getAll.started");
        Page<BasketEntity> basketEntity = basketRepository.findAll(pageable);
        List<BasketDto> basketDto = basketEntity.stream().map(basketMapper::mapToDto).toList();
        log.info("ActionLog.getAll.end");
        return new PageImpl<>(basketDto, basketEntity.getPageable(), basketEntity.getTotalElements());
    }

    @Override
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

    @Override
    public void add(BasketDto basketDto) {
        log.info("ActionLog.add.started: basketDto {}", basketDto);
        validationUtil.checkStockSize(basketDto.getOrderId());
        BasketEntity basketEntity = basketMapper.mapToEntity(basketDto);
        OrderEntity order = orderRepository.findById(basketEntity.getOrder().getId()).orElseThrow(() -> new NotFoundException("orderId not found"));
        if (basketEntity.getStatus().equals(Status.SELECTED)) {
            if (basketEntity.getProductName().equals(Products.SHOES)) {
                ShoesEntity shoes = shoesRepository.findById(basketEntity.getProductId()).orElseThrow(() -> new NotFoundException("productId not found"));
                order.setProductsPrice(shoes.getBalance() + order.getProductsPrice());
            } else if (basketEntity.getProductName().equals(Products.SHIRT)) {
                ShirtEntity shirt = shirtsRepository.findById(basketEntity.getProductId()).orElseThrow(() -> new NotFoundException("productId not found"));
                order.setProductsPrice(shirt.getBalance() + order.getProductsPrice());
            } else if (basketEntity.getProductName().equals(Products.TROUSERS)) {
                TrousersEntity trousers = trousersRepository.findById(basketEntity.getProductId()).orElseThrow(() -> new NotFoundException("productId not found"));
                order.setProductsPrice(trousers.getPrice() + order.getProductsPrice());
            } else {
                ElectricalEquipmentsEntity electricalEquipmentsEntity = electricalEquipmentRepository.findById(basketEntity.getProductId()).orElseThrow(() -> new NotFoundException("productId not found"));
                order.setProductsPrice(electricalEquipmentsEntity.getPrice() + order.getProductsPrice());
            }
        }
        basketRepository.save(basketEntity);
        log.info("ActionLog.add.end: basketDto {}", basketDto);
    }

    @Override
    public void update(Long basketId, Long userId, BasketDto basketDto) {
        log.info("ActionLog.update.started: basketId {},userId {}", basketId, userId);
        var basketEntity = basketRepository.findById(basketId).orElseThrow(() -> new NotFoundException("basketId not found"));
        if (!basketEntity.getUser().getId().equals(userId)) {
            throw new NotFoundException("user not found");
        }
        var updateBasket = basketMapper.mapToEntity(basketEntity, basketDto);
        basketRepository.save(updateBasket);
        log.info("ActionLog.update.started: basketId {},userId {}", basketId, userId);
    }

    @Override
    @Transactional
    public void remove(Long basketId) {
        log.info("ActionLog.remove.started: basketId {}", basketId);
        BasketEntity basket = basketRepository.findByIdAndStatus(basketId, Status.SELECTED).orElseThrow(() -> new NotFoundException("basketId not found"));
        OrderEntity order = basket.getOrder();
        order.getBaskets().remove(basket);
        if (basket.getProductName().equals(Products.SHOES)) {
            ShoesEntity shoes = shoesRepository.findById(basket.getProductId()).orElseThrow(() -> new NotFoundException("productId not found"));
            basket.getOrder().setProductsPrice(basket.getOrder().getProductsPrice() - shoes.getBalance());
        } else if (basket.getProductName().equals(Products.SHIRT)) {
            ShirtEntity shirt = shirtsRepository.findById(basket.getProductId()).orElseThrow(() -> new NotFoundException("productId not found"));
            basket.getOrder().setProductsPrice(basket.getOrder().getProductsPrice() - shirt.getBalance());
        } else if (basket.getProductName().equals(Products.TROUSERS)) {
            TrousersEntity trousers = trousersRepository.findById(basket.getProductId()).orElseThrow(() -> new NotFoundException("productId not found"));
            basket.getOrder().setProductsPrice(basket.getOrder().getProductsPrice() - trousers.getPrice());
        } else {
            ElectricalEquipmentsEntity electricalEquipmentsEntity = electricalEquipmentRepository.findById(basket.getProductId()).orElseThrow(() -> new NotFoundException("productId not found"));
            basket.getOrder().setProductsPrice(basket.getOrder().getProductsPrice() - electricalEquipmentsEntity.getPrice());
        }
        basketRepository.deleteById(basketId);
        log.info("ActionLog.remove.end: basketId {}", basketId);
    }
}
