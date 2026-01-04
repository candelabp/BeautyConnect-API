package com.example.beautyconnectapi.service.impl;

import com.example.beautyconnectapi.config.mapper.SuperAdminMapper;
import com.example.beautyconnectapi.exception.ResourceNotFoundException;
import com.example.beautyconnectapi.model.dto.prestadorDeServicio.PrestadorDeServicioResponseDTO;
import com.example.beautyconnectapi.model.dto.superAdmin.SuperAdminDTO;
import com.example.beautyconnectapi.model.dto.superAdmin.SuperAdminResponseDTO;
import com.example.beautyconnectapi.model.entity.PrestadorDeServicio;
import com.example.beautyconnectapi.model.entity.SuperAdmin;
import com.example.beautyconnectapi.repository.SuperAdminRepository;
import com.example.beautyconnectapi.service.SuperAdminService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SuperAdminImpl implements SuperAdminService {
    private final SuperAdminRepository superAdminRepository;
    private final SuperAdminMapper superAdminMapper;

    public SuperAdminImpl(SuperAdminRepository superAdminRepository, SuperAdminMapper superAdminMapper) {
        this.superAdminRepository = superAdminRepository;
        this.superAdminMapper = superAdminMapper;
    }

    @Override
    @Transactional
    public SuperAdminResponseDTO saveSuperAdmin(SuperAdminDTO superAdminDTO){
        SuperAdmin superAdmin = superAdminMapper.toEntity(superAdminDTO);
        superAdminRepository.save(superAdmin);
        return superAdminMapper.toResponseDTO(superAdmin);
    }

    @Override
    @Transactional(readOnly = true)
    public SuperAdminResponseDTO getSuperAdminById(Long superAdminId){
        SuperAdmin superAdmin = superAdminRepository.findById(superAdminId)
                .orElseThrow(() -> new ResourceNotFoundException("SuperAdmin", superAdminId));
        return superAdminMapper.toResponseDTO(superAdmin);
    }

    @Override
    @Transactional
    public SuperAdminResponseDTO updateSuperAdmin(Long superAdminId,SuperAdminDTO superAdminDTO){
        SuperAdmin superAdmin = superAdminRepository.findById(superAdminId)
                .orElseThrow(() -> new ResourceNotFoundException("SuperAdmin", superAdminId));
        if (!superAdmin.getNombre().equals(superAdminDTO.getNombre())){
            superAdmin.setNombre(superAdminDTO.getNombre());
        }
        if (!superAdmin.getTelefono().equals(superAdminDTO.getTelefono())){
            superAdmin.setTelefono(superAdminDTO.getTelefono());
        }
        superAdminRepository.save(superAdmin);
        return superAdminMapper.toResponseDTO(superAdmin);
    }

    @Override
    @Transactional(readOnly = true)
    public SuperAdminResponseDTO obtenerPorUid(String uid) {
        SuperAdmin superAdmin = superAdminRepository.findByUsuarioUid(uid)
                .orElseThrow(() -> new ResourceNotFoundException("Admin" , uid));
        return superAdminMapper.toResponseDTO(superAdmin);
    }
}
