package com.example.projecttrendshopapp.mapper;

import ch.qos.logback.core.model.INamedModel;
import com.example.projecttrendshopapp.dao.entity.CardsEntity;
import com.example.projecttrendshopapp.model.dto.CardsDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CardMapper {
    CardsEntity mapToEntity(CardsDto cardsDto);

    CardsDto mapToDto(CardsEntity cardsEntity);

    @Mapping(ignore = true, target = "id")
    CardsEntity mapToEntity(@MappingTarget CardsEntity cardsEntity, CardsDto cardsDto);
}
