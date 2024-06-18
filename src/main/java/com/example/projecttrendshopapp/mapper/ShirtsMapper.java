package com.example.projecttrendshopapp.mapper;

import com.example.projecttrendshopapp.dao.entity.ShirtEntity;
import com.example.projecttrendshopapp.model.dto.ShirtDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ShirtsMapper {
    ShirtDto mapToDto(ShirtEntity shirtEntity);

    ShirtEntity mapToEntity(ShirtDto shirtDto);

    @Mapping(ignore = true, target = "id")
    ShirtEntity mapToEntity(@MappingTarget ShirtEntity shirtEntity, ShirtDto shirtDto);
}
