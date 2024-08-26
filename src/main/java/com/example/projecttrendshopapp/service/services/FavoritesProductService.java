package com.example.projecttrendshopapp.service.services;

import com.example.projecttrendshopapp.dao.entity.FavoritesProductEntity;
import com.example.projecttrendshopapp.dao.repository.*;
import com.example.projecttrendshopapp.exception.NotFoundException;
import com.example.projecttrendshopapp.mapper.BasketMapper;
import com.example.projecttrendshopapp.mapper.FavoritesProductsMapper;
import com.example.projecttrendshopapp.model.dto.FavoritesProductDto;
import com.example.projecttrendshopapp.model.enums.Products;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class FavoritesProductService {
    private final FavoritesProductsRepository favoritesProductsRepository;
    private final FavoritesProductsMapper favoritesProductsMapper;
    private final ShirtsRepository shirtsRepository;
    private final ShoesRepository shoesRepository;
    private final TrousersRepository trousersRepository;
    private final ElectricalEquipmentRepository electricalEquipmentRepository;
    private final BasketMapper basketMapper;

    public List<FavoritesProductDto> getAll() {
        log.info("ActionLog.getAll.started.");
        List<FavoritesProductEntity> favoritesProductEntities = favoritesProductsRepository.findAll();
        var getProducts = getProducts(favoritesProductEntities);
        log.info("ActionLog.getAll.end.");
        return getProducts;
    }

    protected List<FavoritesProductDto> getProducts(List<FavoritesProductEntity> favoritesProductEntities) {
        List<Object> products = new ArrayList<>();
        for (FavoritesProductEntity favoritesProductEntity : favoritesProductEntities) {
            if (favoritesProductEntity.getProductName().equals(Products.SHOES)) {
                var product = shoesRepository.findById(favoritesProductEntity.getId()).orElseThrow(() -> new NotFoundException("favoritesProductEntity.getId() not found"));
                products.add(basketMapper.mapShoesEntityToDto(product, favoritesProductEntity.getId()));
            } else if (favoritesProductEntity.getProductName().equals(Products.SHIRT)) {
                var product = shirtsRepository.findById(favoritesProductEntity.getId()).orElseThrow(() -> new NotFoundException("favoritesProductEntity.getId() not found"));
                products.add(basketMapper.mapShirtEntityToDto(product, favoritesProductEntity.getId()));
            } else if (favoritesProductEntity.getProductName().equals(Products.TROUSERS)) {
                var product = trousersRepository.findById(favoritesProductEntity.getId()).orElseThrow(() -> new NotFoundException("favoritesProductEntity.getId() not found"));
                products.add(basketMapper.mapTrousersEntityToDto(product, favoritesProductEntity.getId()));
            } else if (favoritesProductEntity.getProductName().equals(Products.ELECTRICAL_EQUIPMENTS)) {
                var product = electricalEquipmentRepository.findById(favoritesProductEntity.getId()).orElseThrow(() -> new NotFoundException("favoritesProductEntity.getId() not found"));
                products.add(basketMapper.mapElectricalEntityToDto(product, favoritesProductEntity.getId()));
            }
        }
        List<FavoritesProductDto> favoritesProductDto = favoritesProductsMapper.mapToDto(favoritesProductEntities);
        favoritesProductDto.forEach(it -> it.setProducts(products));

        return favoritesProductDto;
    }

    public FavoritesProductDto getById(Long favoritesProductId) {
        log.info("ActionLog.getById.started:favoritesProductId {}", favoritesProductId);
        List<Object> product = new ArrayList<>();
        FavoritesProductEntity favoritesProductEntity = favoritesProductsRepository.findById(favoritesProductId).orElseThrow(() -> new NotFoundException("favoritesProductId not found"));
        if (favoritesProductEntity.getProductName().equals(Products.SHIRT)) {
            var shirtEntity = shirtsRepository.findById(favoritesProductEntity.getProductId()).orElseThrow(() -> new NotFoundException("favoritesProductEntity.getProductId() not found"));
            product.add(shirtEntity);
        } else if (favoritesProductEntity.getProductName().equals(Products.SHOES)) {
            var shoesEntity = shoesRepository.findById(favoritesProductEntity.getProductId()).orElseThrow(() -> new NotFoundException("favoritesProductEntity.getProductId() not found"));
            product.add(shoesEntity);
        } else if (favoritesProductEntity.getProductName().equals(Products.TROUSERS)) {
            var trousersEntity = trousersRepository.findById(favoritesProductEntity.getProductId()).orElseThrow(() -> new NotFoundException("favoritesProductEntity.getProductId() not found"));
            product.add(trousersEntity);
        } else if (favoritesProductEntity.getProductName().equals(Products.ELECTRICAL_EQUIPMENTS)) {
            var electricalEquipmentsEntity = electricalEquipmentRepository.findById(favoritesProductEntity.getProductId()).orElseThrow(() -> new NotFoundException("favoritesProductEntity.getProductId() not found"));
            product.add(electricalEquipmentsEntity);
        }
        FavoritesProductDto favoritesProductDto = favoritesProductsMapper.mapToDto(favoritesProductEntity);
        favoritesProductDto.setProducts(product);
        log.info("ActionLog.getById.end:favoritesProductId {}", favoritesProductId);
        return favoritesProductDto;
    }

    public void create(FavoritesProductDto favoritesProductDto) {
        log.info("ActionLog.create.started:favoritesProductDto {}", favoritesProductDto);
        FavoritesProductEntity favoritesProductEntity = favoritesProductsMapper.mapToEntity(favoritesProductDto);
        favoritesProductsRepository.save(favoritesProductEntity);
        log.info("ActionLog.create.end:favoritesProductDto {}", favoritesProductDto);
    }


    public void remove(Long favoritesProductId) {
        log.info("ActionLog.remove.started: favoritesProductId {}", favoritesProductId);
        favoritesProductsRepository.deleteById(favoritesProductId);
        log.info("ActionLog.remove.end: favoritesProductId {}", favoritesProductId);
    }
}
