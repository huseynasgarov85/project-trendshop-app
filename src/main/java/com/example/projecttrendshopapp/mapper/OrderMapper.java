package com.example.projecttrendshopapp.mapper;

import com.example.projecttrendshopapp.dao.entity.OrderEntity;
import com.example.projecttrendshopapp.model.dto.BasketDto;
import com.example.projecttrendshopapp.model.dto.OrderDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface OrderMapper {
//    @Mapping(source = "user.id", target = "userId")
//    @Mapping(source = "", target = "basketDtos")
//    OrderDto basketToOrder(BasketDto basketDto);

    OrderDto mapToDto(OrderEntity orderEntity);

    OrderEntity mapToEntity(OrderDto orderDto);

    @Mapping(ignore = true, target = "id")
    OrderEntity mapToEntity(@MappingTarget OrderEntity orderEntity, OrderDto orderDto);
}
