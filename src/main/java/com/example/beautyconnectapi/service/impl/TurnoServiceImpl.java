package com.example.beautyconnectapi.service.impl;

import com.example.beautyconnectapi.config.mapper.TurnoMapper;
import com.example.beautyconnectapi.model.dto.turno.TurnoDTO;
import com.example.beautyconnectapi.model.entity.Turno;
import com.example.beautyconnectapi.model.enums.Estado;
import com.example.beautyconnectapi.repository.TurnoRepository;
import com.example.beautyconnectapi.service.TurnoService;
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
    public TurnoDTO crearTurno(TurnoDTO dto) {
        Turno turno = turnoMapper.toEntity(dto);
        turno.setEstado(Estado.PENDIENTE);
        return turnoMapper.toDto(turnoRepository.save(turno));
    }

    @Override
    public List<TurnoDTO> listarPorCliente(Long clienteId) {
        return turnoRepository.findByClienteId(clienteId)
                .stream()
                .map(turnoMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TurnoDTO> listarPorPrestador(Long prestadorId) {
        return turnoRepository.findByPrestadorId(prestadorId)
                .stream()
                .map(turnoMapper::toDto)
                .collect(Collectors.toList());
    }

}
