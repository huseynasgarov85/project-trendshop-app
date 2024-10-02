package com.example.projecttrendshopapp.mapper;

import com.example.projecttrendshopapp.dao.entity.*;
import com.example.projecttrendshopapp.dto.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface BasketMapper {
    @Mapping(source = "order.id",target = "orderId")
    BasketDto mapToDto(BasketEntity basketEntity);

    @Mapping(source = "productId", target = "product", ignore = true)
    BasketWithProductsDto mapToDtoV2(BasketEntity basketEntity);

    @Mapping(source = "orderId", target = "order.id")
    BasketEntity mapToEntity(BasketDto basketDto);

    BasketEntity mapToEntity(@MappingTarget BasketEntity basketEntity, BasketDto basketDto);

    ShoesDto mapShoesEntityToDto(ShoesEntity shoesEntity, Long basketId);

    TrousersDto mapTrousersEntityToDto(TrousersEntity trousersEntity, Long basketId);

    ElectricalEquipmentsDto mapElectricalEntityToDto(ElectricalEquipmentsEntity electricalEquipmentsEntity, Long basketId);

    ShirtDto mapShirtEntityToDto(ShirtEntity shirtEntity, Long basketId);
}
