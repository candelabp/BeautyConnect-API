package com.example.beautyconnectapi.service.impl;

import com.example.beautyconnectapi.config.mapper.CentroDeEsteticaMapper;
import com.example.beautyconnectapi.model.dto.centroDeEstetica.CentroDeEsteticaDTO;
import com.example.beautyconnectapi.model.dto.centroDeEstetica.CentroDeEsteticaResponseDTO;
import com.example.beautyconnectapi.model.entity.CentroDeEstetica;
import com.example.beautyconnectapi.model.enums.Estado;
import com.example.beautyconnectapi.repository.CentroDeEsteticaRepository;
import com.example.beautyconnectapi.repository.DomicilioRepository;
import com.example.beautyconnectapi.service.CentroDeEsteticaService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CentroDeEsteticaServiceImpl implements CentroDeEsteticaService {
    private final CentroDeEsteticaRepository centroDeEsteticaRepository;
    private final CentroDeEsteticaMapper centroDeEsteticaMapper;
    private final DomicilioRepository domicilioRepository;

    public CentroDeEsteticaServiceImpl(CentroDeEsteticaRepository centroDeEsteticaRepository, CentroDeEsteticaMapper centroDeEsteticaMapper, DomicilioRepository domicilioRepository) {
        this.centroDeEsteticaRepository = centroDeEsteticaRepository;
        this.centroDeEsteticaMapper = centroDeEsteticaMapper;
        this.domicilioRepository = domicilioRepository;
    }

    @Override
    @Transactional
    public CentroDeEsteticaResponseDTO registrar(CentroDeEsteticaDTO centroDeEsteticadto) {
        CentroDeEstetica centroDeEstetica = centroDeEsteticaMapper.toEntity(centroDeEsteticadto);
        centroDeEstetica.setEstado(Estado.PENDIENTE);
        centroDeEstetica.getServicios()
                .forEach(servicio -> {servicio.setCentroDeEstetica(centroDeEstetica);
        });
        centroDeEstetica.setDomicilio(domicilioRepository.findById(centroDeEsteticadto.getDomicilio_id())
        .orElseThrow(()  -> new RuntimeException("Domicilio no encontrado"))) ;
        return centroDeEsteticaMapper.toResponseDTO(centroDeEsteticaRepository.save(centroDeEstetica));
    }

    @Override
    @Transactional(readOnly = true)
    public List<CentroDeEsteticaResponseDTO> listar() {
        return centroDeEsteticaRepository.findAll().stream()
                .map(centroDeEsteticaMapper::toResponseDTO)
                .collect(Collectors.toList());

    }

    @Override
    @Transactional(readOnly = true)
    public List<CentroDeEsteticaResponseDTO> listarPorEstado(Estado estado) {
        return centroDeEsteticaRepository.findByEstado(estado).stream()
                .map(centroDeEsteticaMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public CentroDeEsteticaResponseDTO cambiarEstado(Long id, Estado estado) {
        CentroDeEstetica centroDeEstetica = centroDeEsteticaRepository.findById(id).orElse(null);
        centroDeEstetica.setEstado(estado);
        return centroDeEsteticaMapper.toResponseDTO(centroDeEstetica);
    }

    @Override
    @Transactional
    public CentroDeEsteticaResponseDTO actualizar(Long id, CentroDeEsteticaDTO dto) {
        CentroDeEstetica entity = centroDeEsteticaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Centro no encontrado"));

        CentroDeEstetica actualizado = centroDeEsteticaMapper.toEntity(dto);
        actualizado.setId(id);
        return centroDeEsteticaMapper.toResponseDTO(centroDeEsteticaRepository.save(actualizado));
    }


    @Override
    @Transactional(readOnly = true)
    public CentroDeEsteticaResponseDTO obtenerPorId(Long id) {
        return centroDeEsteticaRepository.findById(id)
                .map(centroDeEsteticaMapper::toResponseDTO)
                .orElseThrow(() -> new EntityNotFoundException("Centro no encontrado"));
    }

    @Override
    @Transactional(readOnly = true)
    public Long obtenerIdPorUid(String uid) {
        Long id = centroDeEsteticaRepository.findIdByUsuarioUid(uid);
        if (id == null) {
            throw new RuntimeException("No se encontró Centro de Estética para el uid " + uid);
        }
        return id;
    }


}
