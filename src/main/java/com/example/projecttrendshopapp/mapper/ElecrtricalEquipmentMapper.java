package com.example.projecttrendshopapp.mapper;

import com.example.projecttrendshopapp.dao.entity.ElectricalEquipmentsEntity;
import com.example.projecttrendshopapp.model.dto.ElectricalEquipmentsDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ElecrtricalEquipmentMapper {
    ElectricalEquipmentsDto mapToDto(ElectricalEquipmentsEntity electricalEquipmentsEntity);

    ElectricalEquipmentsEntity mapToEntity(ElectricalEquipmentsDto electricalEquipmentsDto);

    @Mapping(ignore = true, target = "id")
    ElectricalEquipmentsEntity mapToEntity(@MappingTarget ElectricalEquipmentsEntity electricalEquipmentsEntity, ElectricalEquipmentsDto electricalEquipmentsDto);
}
