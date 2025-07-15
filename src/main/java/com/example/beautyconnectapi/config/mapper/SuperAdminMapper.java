package com.example.beautyconnectapi.config.mapper;

import com.example.beautyconnectapi.model.dto.superAdmin.SuperAdminDTO;
import com.example.beautyconnectapi.model.dto.superAdmin.SuperAdminResponseDTO;
import com.example.beautyconnectapi.model.entity.SuperAdmin;

public interface SuperAdminMapper {
    SuperAdmin toEntity(SuperAdminDTO superAdminDTO);
    SuperAdminResponseDTO toResponseDTO(SuperAdmin superAdmin);
}
