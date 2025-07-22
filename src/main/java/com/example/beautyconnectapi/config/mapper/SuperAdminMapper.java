package com.example.beautyconnectapi.config.mapper;

import com.example.beautyconnectapi.model.dto.superAdmin.SuperAdminDTO;
import com.example.beautyconnectapi.model.dto.superAdmin.SuperAdminResponseDTO;
import com.example.beautyconnectapi.model.entity.SuperAdmin;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SuperAdminMapper {
    @Mapping(target = "id", ignore = true)
    SuperAdmin toEntity(SuperAdminDTO superAdminDTO);
    SuperAdminResponseDTO toResponseDTO(SuperAdmin superAdmin);
}
