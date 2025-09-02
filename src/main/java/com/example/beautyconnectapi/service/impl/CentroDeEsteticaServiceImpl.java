package com.example.beautyconnectapi.service.impl;

import com.example.beautyconnectapi.config.mapper.CentroDeEsteticaMapper;
import com.example.beautyconnectapi.model.dto.centroDeEstetica.CentroDeEsteticaDTO;
import com.example.beautyconnectapi.model.dto.centroDeEstetica.CentroDeEsteticaResponseDTO;
import com.example.beautyconnectapi.model.entity.CentroDeEstetica;
import com.example.beautyconnectapi.model.enums.Estado;
import com.example.beautyconnectapi.repository.CentroDeEsteticaRepository;
import com.example.beautyconnectapi.service.CentroDeEsteticaService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CentroDeEsteticaServiceImpl implements CentroDeEsteticaService {
    private final CentroDeEsteticaRepository centroDeEsteticaRepository;
    private final CentroDeEsteticaMapper centroDeEsteticaMapper;

    public CentroDeEsteticaServiceImpl(CentroDeEsteticaRepository centroDeEsteticaRepository, CentroDeEsteticaMapper centroDeEsteticaMapper) {
        this.centroDeEsteticaRepository = centroDeEsteticaRepository;
        this.centroDeEsteticaMapper = centroDeEsteticaMapper;
    }

    @Override
    public CentroDeEsteticaResponseDTO registrar(CentroDeEsteticaDTO centroDeEsteticadto) {
        CentroDeEstetica centroDeEstetica = centroDeEsteticaMapper.toEntity(centroDeEsteticadto);
        centroDeEstetica.setEstado(Estado.PENDIENTE);
        return centroDeEsteticaMapper.toResponseDTO(centroDeEsteticaRepository.save(centroDeEstetica));
    }

    @Override
    public List<CentroDeEsteticaResponseDTO> listar() {
        return centroDeEsteticaRepository.findAll().stream()
                .map(centroDeEsteticaMapper::toResponseDTO)
                .collect(Collectors.toList());

    }

    @Override
    public List<CentroDeEsteticaResponseDTO> listarPorEstado(Estado estado) {
        return centroDeEsteticaRepository.findByEstado(estado).stream()
                .map(centroDeEsteticaMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CentroDeEsteticaResponseDTO cambiarEstado(Long id, Estado estado) {
        CentroDeEstetica centroDeEstetica = centroDeEsteticaRepository.findById(id).orElse(null);
        centroDeEstetica.setEstado(estado);
        return centroDeEsteticaMapper.toResponseDTO(centroDeEstetica);
    }

    @Override
    public CentroDeEsteticaResponseDTO actualizar(Long id, CentroDeEsteticaDTO dto) {
        CentroDeEstetica entity = centroDeEsteticaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Centro no encontrado"));

        CentroDeEstetica actualizado = centroDeEsteticaMapper.toEntity(dto);
        actualizado.setId(id);
        return centroDeEsteticaMapper.toResponseDTO(centroDeEsteticaRepository.save(actualizado));
    }


    @Override
    public CentroDeEsteticaResponseDTO obtenerPorId(Long id) {
        return centroDeEsteticaRepository.findById(id)
                .map(centroDeEsteticaMapper::toResponseDTO)
                .orElseThrow(() -> new EntityNotFoundException("Centro no encontrado"));
    }


}
