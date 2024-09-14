package com.example.projecttrendshopapp.mapper;

import com.example.projecttrendshopapp.dao.entity.FavoritesProductEntity;
import com.example.projecttrendshopapp.dto.FavoritesProductDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FavoritesProductsMapper {
    FavoritesProductEntity mapToEntity(FavoritesProductDto favoritesProductDto);

    List<FavoritesProductDto> mapToDto(List<FavoritesProductEntity> favoritesProductEntity);

    FavoritesProductDto mapToDto(FavoritesProductEntity favoritesProductEntity);
}
