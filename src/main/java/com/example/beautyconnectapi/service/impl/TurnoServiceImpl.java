package com.example.beautyconnectapi.service.impl;

import com.example.beautyconnectapi.config.mapper.TurnoMapper;
import com.example.beautyconnectapi.model.dto.turno.TurnoDTO;
import com.example.beautyconnectapi.model.dto.turno.TurnoResponseDTO;
import com.example.beautyconnectapi.model.entity.Turno;
import com.example.beautyconnectapi.model.enums.Estado;
import com.example.beautyconnectapi.model.enums.EstadoTurno;
import com.example.beautyconnectapi.repository.CentroDeEsteticaRepository;
import com.example.beautyconnectapi.repository.ClienteRepository;
import com.example.beautyconnectapi.repository.ProfesionalServicioRepository;
import com.example.beautyconnectapi.repository.TurnoRepository;
import com.example.beautyconnectapi.service.TurnoService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TurnoServiceImpl implements TurnoService {

    private final TurnoRepository turnoRepository;
    private final TurnoMapper turnoMapper;
    private final ClienteRepository clienteRepository;
    private final ProfesionalServicioRepository profesionalServicioRepository;
    private final CentroDeEsteticaRepository centroDeEsteticaRepository;



    public TurnoServiceImpl(TurnoRepository turnoRepository, TurnoMapper turnoMapper, ClienteRepository clienteRepository, ProfesionalServicioRepository profesionalServicioRepository,
                            CentroDeEsteticaRepository centroDeEsteticaRepository) {
        this.turnoRepository = turnoRepository;
        this.turnoMapper = turnoMapper;
        this.clienteRepository = clienteRepository;
        this.profesionalServicioRepository = profesionalServicioRepository;
        this.centroDeEsteticaRepository = centroDeEsteticaRepository;
    }

    @Override
    @Transactional
    public TurnoResponseDTO crear(TurnoDTO dto) {
        Turno turno = turnoMapper.toEntity(dto);
        turno.setEstado(EstadoTurno.PENDIENTE);
        turno.setCliente(clienteRepository.findById(dto.getClienteId())
                .orElseThrow(()  -> new RuntimeException("Cliente no encontrado"))) ;
        turno.setProfesionalServicio(profesionalServicioRepository.findById(dto.getProfesionalServicioId())
                .orElseThrow(()  -> new RuntimeException("ProfesionalServicio no encontrado"))) ;
        turno.setCentroDeEstetica(centroDeEsteticaRepository.findById(dto.getCentroId())
         .orElseThrow(()  -> new RuntimeException("ProfesionalServicio no encontrado")));
        return turnoMapper.toResponseDTO(turnoRepository.save(turno));
    }

    @Override
    @Transactional(readOnly = true)
    public List<TurnoResponseDTO> listarTodos() {
        return turnoRepository.findAll().stream()
                .map(turnoMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<TurnoResponseDTO> listarPorCliente(Long clienteId) {
        return turnoRepository.findByClienteId(clienteId).stream()
                .map(turnoMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

//    @Override
//    public List<TurnoResponseDTO> listarPorPrestador(Long prestadorId) {
//        return turnoRepository.findByPrestadorId(prestadorId).stream()
//                .map(turnoMapper::toResponseDTO)
//                .collect(Collectors.toList());
//    }

    @Override
    @Transactional
    public TurnoResponseDTO cambiarEstado(Long turnoId, EstadoTurno nuevoEstado) {
        Turno turno = turnoRepository.findById(turnoId)
                .orElseThrow(() -> new RuntimeException("Turno no encontrado"));
        turno.setEstado(nuevoEstado);
        return turnoMapper.toResponseDTO(turnoRepository.save(turno));
    }

    @Override
    @Transactional(readOnly = true)
    public TurnoResponseDTO obtenerPorId(Long id) {
        return turnoRepository.findById(id)
                .map(turnoMapper::toResponseDTO)
                .orElseThrow(() -> new EntityNotFoundException("Turno no encontrado"));
    }

    @Override
    @Transactional
    public TurnoResponseDTO actualizar(Long id, TurnoDTO dto) {
        Turno existente = turnoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Turno no encontrado"));

        Turno actualizado = turnoMapper.toEntity(dto);
        actualizado.setId(id);
        return turnoMapper.toResponseDTO(turnoRepository.save(actualizado));
    }
    @Override
    @Transactional
    public List<TurnoResponseDTO>obtenerPorCentro(Long centroId){
        return turnoRepository.findByCentroDeEsteticaId(centroId).stream()
                .map(turnoMapper::toResponseDTO)
                .collect(Collectors.toList());
    }


}