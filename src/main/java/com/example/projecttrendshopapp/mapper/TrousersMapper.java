package com.example.projecttrendshopapp.mapper;

import com.example.projecttrendshopapp.dao.entity.TrousersEntity;
import com.example.projecttrendshopapp.model.dto.TrousersDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TrousersMapper {
    TrousersDto mapToDto(TrousersEntity trousersEntity);

    TrousersEntity mapToEntity(TrousersDto trousersDto);

    @Mapping(target = "id", ignore = true)
    TrousersEntity mapToEntity(@MappingTarget TrousersEntity trousersEntity, TrousersDto trousersDto);
}
