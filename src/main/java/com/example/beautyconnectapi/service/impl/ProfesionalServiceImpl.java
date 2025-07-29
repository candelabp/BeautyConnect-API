package com.example.beautyconnectapi.service.impl;

import com.example.beautyconnectapi.config.mapper.ProfesionalMapper;
import com.example.beautyconnectapi.model.dto.profesional.ProfesionalDTO;
import com.example.beautyconnectapi.model.dto.profesional.ProfesionalResponseDTO;
import com.example.beautyconnectapi.model.entity.Profesional;
import com.example.beautyconnectapi.repository.ProfesionalRepository;
import com.example.beautyconnectapi.service.ProfesionalService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProfesionalServiceImpl implements ProfesionalService {
    private final ProfesionalRepository profesionalRepository;
    private final ProfesionalMapper profesionalMapper;

    public ProfesionalServiceImpl(ProfesionalRepository profesionalRepository, ProfesionalMapper profesionalMapper) {
        this.profesionalRepository = profesionalRepository;
        this.profesionalMapper = profesionalMapper;
    }

    @Override
    @Transactional
    public ProfesionalResponseDTO saveProfesional(ProfesionalDTO profesionalDto){
        Profesional profesional = profesionalMapper.toEntity(profesionalDto);
        profesionalRepository.save(profesional);
        return profesionalMapper.toResponseDTO(profesional);
    }

    @Override
    @Transactional
    public ProfesionalResponseDTO getProfesionalById(Long profesionalId){
        Profesional profesional = profesionalRepository.findById(profesionalId)
                .orElseThrow(() -> new RuntimeException("Profesional no encontrado"));
        return profesionalMapper.toResponseDTO(profesional);
    }

    @Override
    @Transactional
    public ProfesionalResponseDTO updateProfesional(Long profesionalId, ProfesionalDTO profesionalDto){
        Profesional profesional = profesionalRepository.findById(profesionalId)
                .orElseThrow(() -> new RuntimeException("Profesional no encontrado"));

        if(!profesional.getNombre().equals(profesionalDto.getNombre())){
            profesional.setNombre(profesionalDto.getNombre());
        }

        profesionalRepository.save(profesional);
        return profesionalMapper.toResponseDTO(profesional);
    }

    @Override
    @Transactional
    public ProfesionalResponseDTO deleteProfesional(Long profesionalId){
        Profesional profesional = profesionalRepository.findById(profesionalId)
                .orElseThrow(() -> new RuntimeException("Profesional no encontrado"));

        profesional.setActive(false);
        profesionalRepository.save(profesional);
        return profesionalMapper.toResponseDTO(profesional);
    }
}
