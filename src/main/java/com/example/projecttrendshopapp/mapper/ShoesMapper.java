package com.example.projecttrendshopapp.mapper;

import com.example.projecttrendshopapp.dao.entity.ShoesEntity;
import com.example.projecttrendshopapp.model.dto.ShoesDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ShoesMapper {
    ShoesDto mapToDto(ShoesEntity shoesEntity);

    ShoesEntity mapToEntity(ShoesDto shoesDto);

    @Mapping(ignore = true, target = "id")
    ShoesEntity mapToEntity(@MappingTarget ShoesEntity shoesEntity, ShoesDto shoesDto);
}
