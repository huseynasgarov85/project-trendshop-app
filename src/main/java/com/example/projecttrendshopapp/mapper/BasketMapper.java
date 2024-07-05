package com.example.projecttrendshopapp.mapper;

import com.example.projecttrendshopapp.dao.entity.BasketEntity;
import com.example.projecttrendshopapp.model.dto.BasketDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface BasketMapper {
   BasketDto mapToDto(BasketEntity basketEntity);
   BasketDto mapToDto(BasketEntity basketEntity,Object product);

    BasketEntity mapToEntity(BasketDto basketDto);

    BasketEntity mapToEntity(@MappingTarget BasketEntity basketEntity, BasketDto basketDto);
}
