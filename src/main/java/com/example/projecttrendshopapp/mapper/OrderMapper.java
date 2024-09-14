package com.example.projecttrendshopapp.mapper;

import com.example.projecttrendshopapp.dao.entity.BasketEntity;
import com.example.projecttrendshopapp.dao.entity.OrderEntity;
import com.example.projecttrendshopapp.dto.OrderDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface OrderMapper {
    @Mapping(source = "users.id", target = "userId")
//    @Mapping(source = "baskets.id",target = "basketId")
    @Mapping(source = "id", target = "orderId")
    OrderDto mapToDto(OrderEntity orderEntity);

    default List<Long> map(List<BasketEntity> baskets) {
        return baskets.stream().map(BasketEntity::getId).collect(Collectors.toList());
    }
}
