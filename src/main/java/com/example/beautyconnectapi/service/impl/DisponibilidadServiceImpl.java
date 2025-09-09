package com.example.beautyconnectapi.service.impl;

import com.example.beautyconnectapi.config.mapper.DisponibilidadMapper;
import com.example.beautyconnectapi.model.dto.disponibilidad.DisponibilidadDTO;
import com.example.beautyconnectapi.model.dto.disponibilidad.DisponibilidadResponseDTO;
import com.example.beautyconnectapi.model.entity.Disponibilidad;
import com.example.beautyconnectapi.repository.DisponibilidadRepository;
import com.example.beautyconnectapi.service.DisponibilidadService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DisponibilidadServiceImpl implements DisponibilidadService {
    private final DisponibilidadRepository disponibilidadRepository;
    private final DisponibilidadMapper disponibilidadMapper;

    public DisponibilidadServiceImpl(DisponibilidadRepository disponibilidadRepository, DisponibilidadMapper disponibilidadMapper) {
        this.disponibilidadRepository = disponibilidadRepository;
        this.disponibilidadMapper = disponibilidadMapper;
    }

    @Override
    @Transactional
    public DisponibilidadResponseDTO saveDisponibilidad(DisponibilidadDTO disponibilidadDto){
        Disponibilidad disponibilidad = disponibilidadMapper.toEntity(disponibilidadDto);
        disponibilidadRepository.save(disponibilidad);
        return disponibilidadMapper.toResponseDTO(disponibilidad);
    }

    @Override
    @Transactional(readOnly = true)
    public DisponibilidadResponseDTO getDisponibilidadById(Long disponibilidadId){
        Disponibilidad disponibilidad = disponibilidadRepository.findById(disponibilidadId)
                .orElseThrow(() -> new RuntimeException("Disponibilidad no encontrada"));

        return disponibilidadMapper.toResponseDTO(disponibilidad);
    }

    @Override
    @Transactional
    public DisponibilidadResponseDTO updateDisponibilidad(Long disponibilidadId, DisponibilidadDTO disponibilidadDto){
        Disponibilidad disponibilidad = disponibilidadRepository.findById(disponibilidadId)
                .orElseThrow(() -> new RuntimeException("Disponibilidad no encontrada"));

        if(!disponibilidad.getFecha().equals(disponibilidadDto.getFecha())){
            disponibilidad.setFecha(disponibilidadDto.getFecha());
        }
        if(!disponibilidad.getHoraInicio().equals(disponibilidadDto.getHoraInicio())){
            disponibilidad.setHoraInicio(disponibilidadDto.getHoraInicio());
        }
        if(!disponibilidad.getHoraFinalizacion().equals(disponibilidadDto.getHoraFinalizacion())){
            disponibilidad.setHoraFinalizacion(disponibilidadDto.getHoraFinalizacion());
        }
        disponibilidadRepository.save(disponibilidad);
        return disponibilidadMapper.toResponseDTO(disponibilidad);
    }

    @Override
    @Transactional
    public DisponibilidadResponseDTO deleteDisponibilidad(Long disponibilidadId){
        Disponibilidad disponibilidad = disponibilidadRepository.findById(disponibilidadId)
                .orElseThrow(() -> new RuntimeException("Disponibilidad no encontrada"));

        disponibilidad.setActive(false);
        disponibilidadRepository.save(disponibilidad);
        return disponibilidadMapper.toResponseDTO(disponibilidad);
    }
}
