package com.example.projecttrendshopapp.mapper;

import com.example.projecttrendshopapp.dao.entity.UsersEntity;
import com.example.projecttrendshopapp.model.dto.UsersDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UsersMapper {

    UsersDto mapToDto(UsersEntity usersEntity);

    UsersEntity mapToEntity(UsersDto usersDto);

    @Mapping(ignore = true, target = "id")
    UsersEntity mapToEntity(@MappingTarget UsersEntity usersEntity, UsersDto usersDto);
}
