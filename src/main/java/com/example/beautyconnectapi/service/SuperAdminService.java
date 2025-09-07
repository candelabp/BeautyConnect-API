package com.example.beautyconnectapi.service;

import com.example.beautyconnectapi.model.dto.superAdmin.SuperAdminDTO;
import com.example.beautyconnectapi.model.dto.superAdmin.SuperAdminResponseDTO;

public interface SuperAdminService {
    SuperAdminResponseDTO saveSuperAdmin(SuperAdminDTO superAdminDTO);
    SuperAdminResponseDTO getSuperAdminById(Long superAdminId);
    SuperAdminResponseDTO updateSuperAdmin(Long superAdminId, SuperAdminDTO superAdminDTO);
}
