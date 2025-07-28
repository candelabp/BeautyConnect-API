package com.example.beautyconnectapi.service.impl;

import com.example.beautyconnectapi.config.mapper.TurnoMapper;
import com.example.beautyconnectapi.model.dto.turno.TurnoDTO;
import com.example.beautyconnectapi.model.dto.turno.TurnoResponseDTO;
import com.example.beautyconnectapi.model.entity.Turno;
import com.example.beautyconnectapi.model.enums.Estado;
import com.example.beautyconnectapi.repository.TurnoRepository;
import com.example.beautyconnectapi.service.TurnoService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TurnoServiceImpl implements TurnoService {

    private final TurnoRepository turnoRepository;
    private final TurnoMapper turnoMapper;

    public TurnoServiceImpl(TurnoRepository turnoRepository, TurnoMapper turnoMapper) {
        this.turnoRepository = turnoRepository;
        this.turnoMapper = turnoMapper;
    }

    @Override
    public TurnoResponseDTO crear(TurnoDTO dto) {
        Turno turno = turnoMapper.toEntity(dto);
        turno.setEstado(Estado.PENDIENTE);
        return turnoMapper.toResponseDTO(turnoRepository.save(turno));
    }

    @Override
    public List<TurnoResponseDTO> listarTodos() {
        return turnoRepository.findAll().stream()
                .map(turnoMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<TurnoResponseDTO> listarPorCliente(Long clienteId) {
        return turnoRepository.findByClienteId(clienteId).stream()
                .map(turnoMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<TurnoResponseDTO> listarPorPrestador(Long prestadorId) {
        return turnoRepository.findByPrestadorId(prestadorId).stream()
                .map(turnoMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TurnoResponseDTO cambiarEstado(Long turnoId, Estado nuevoEstado) {
        Turno turno = turnoRepository.findById(turnoId)
                .orElseThrow(() -> new RuntimeException("Turno no encontrado"));
        turno.setEstado(nuevoEstado);
        return turnoMapper.toResponseDTO(turnoRepository.save(turno));
    }

    @Override
    public TurnoResponseDTO obtenerPorId(Long id) {
        return turnoRepository.findById(id)
                .map(turnoMapper::toResponseDTO)
                .orElseThrow(() -> new EntityNotFoundException("Turno no encontrado"));
    }

    @Override
    public TurnoResponseDTO actualizar(Long id, TurnoDTO dto) {
        Turno existente = turnoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Turno no encontrado"));

        Turno actualizado = turnoMapper.toEntity(dto);
        actualizado.setId(id);
        return turnoMapper.toResponseDTO(turnoRepository.save(actualizado));
    }

}