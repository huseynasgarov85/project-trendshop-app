package com.example.projecttrendshopapp.mapper;

import com.example.projecttrendshopapp.dao.entity.*;
import com.example.projecttrendshopapp.model.dto.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface BasketMapper {
    BasketDto mapToDto(BasketEntity basketEntity);

    @Mapping(source = "productId", target = "product", ignore = true)
    BasketWithProductsDto mapToDtoV2(BasketEntity basketEntity);

    BasketEntity mapToEntity(BasketDto basketDto);

    BasketEntity mapToEntity(@MappingTarget BasketEntity basketEntity, BasketDto basketDto);
    ShoesDtoVol2 mapShoesEntityToDto(ShoesEntity shoesEntity,Long basketId);
    TrousersDtoVol2 mapTrousersEntityToDto(TrousersEntity trousersEntity,Long basketId);
    ElectricalEquipmentsDtoVol2 mapElectricalEntityToDto(ElectricalEquipmentsEntity electricalEquipmentsEntity,Long basketId);
    ShirtDtoVol2 mapShirtEntityToDto(ShirtEntity shirtEntity,Long basketId);
}
