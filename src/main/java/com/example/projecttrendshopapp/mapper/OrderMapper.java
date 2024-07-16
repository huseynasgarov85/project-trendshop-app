package com.example.projecttrendshopapp.mapper;

import com.example.projecttrendshopapp.dao.entity.BasketEntity;
import com.example.projecttrendshopapp.dao.entity.OrderEntity;
import com.example.projecttrendshopapp.model.dto.BasketDto;
import com.example.projecttrendshopapp.model.dto.OrderDto;
import org.mapstruct.*;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface OrderMapper {
    @Mapping(source = "users.id", target = "userId")
  //  @Mapping(source = "baskets", target = "basketList", qualifiedByName = "mapToBasketList")
    OrderDto mapToDto(OrderEntity orderEntity);

//    OrderEntity mapToEntity(OrderDto orderDto);

//    @Mapping(ignore = true, target = "id")
//    OrderEntity mapToEntity(@MappingTarget OrderEntity orderEntity, OrderDto orderDto);

//    @Named(value = "mapToBasketList")
//    default List<Object> mapToBasketList(List<BasketEntity> basketEntityList){
//        return new ArrayList<>(basketEntityList);
//    }


}
