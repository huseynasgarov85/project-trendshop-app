package com.example.projecttrendshopapp.service.serviceImpl.favoritesProduct;

import com.example.projecttrendshopapp.dto.FavoritesProductDto;

import java.util.List;

public interface FavoritesProductService {
    List<FavoritesProductDto> getAll();

    FavoritesProductDto getById(Long favoritesProductId);

    void create(FavoritesProductDto favoritesProductDto);

    void remove(Long favoritesProductId);
}
