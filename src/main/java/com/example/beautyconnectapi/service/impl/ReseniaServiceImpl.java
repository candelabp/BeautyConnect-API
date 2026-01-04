package com.example.beautyconnectapi.service.impl;

import com.example.beautyconnectapi.config.mapper.ReseniaMapper;
import com.example.beautyconnectapi.exception.ResourceNotFoundException;
import com.example.beautyconnectapi.model.dto.resenia.ReseniaDTO;
import com.example.beautyconnectapi.model.dto.resenia.ReseniaResponseDTO;
import com.example.beautyconnectapi.model.dto.turno.TurnoDTO;
import com.example.beautyconnectapi.model.dto.turno.TurnoResponseDTO;
import com.example.beautyconnectapi.model.entity.Resenia;
import com.example.beautyconnectapi.model.entity.Turno;
import com.example.beautyconnectapi.model.enums.EstadoTurno;
import com.example.beautyconnectapi.repository.CentroDeEsteticaRepository;
import com.example.beautyconnectapi.repository.ClienteRepository;
import com.example.beautyconnectapi.repository.ReseniaRepository;
import com.example.beautyconnectapi.repository.TurnoRepository;
import com.example.beautyconnectapi.service.ReseniaService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReseniaServiceImpl implements ReseniaService {
    private final ReseniaRepository reseniaRepository;
    private final ReseniaMapper reseniaMapper;
    private final ClienteRepository clienteRepository;
    private  final CentroDeEsteticaRepository centroDeEsteticaRepository;
    private final TurnoRepository turnoRepository;

    public ReseniaServiceImpl( ReseniaRepository reseniaRepository, ReseniaMapper reseniaMapper, ClienteRepository clienteRepository, CentroDeEsteticaRepository centroDeEsteticaRepository,
                               TurnoRepository turnoRepository){
        this.reseniaMapper = reseniaMapper;
        this.reseniaRepository = reseniaRepository;
        this.centroDeEsteticaRepository = centroDeEsteticaRepository;
        this.clienteRepository = clienteRepository;
        this.turnoRepository = turnoRepository;
    }
    @Override
    @Transactional
    public ReseniaResponseDTO crear(ReseniaDTO dto){
        Resenia resenia = reseniaMapper.toEntity(dto);
        resenia.setCliente(clienteRepository.findById(dto.getClienteId())
                .orElseThrow(()  -> new ResourceNotFoundException("Cliente", dto.getClienteId()))) ;
        resenia.setCentroDeEstetica(centroDeEsteticaRepository.findById(dto.getCentroDeEsteticaId())
                .orElseThrow(()  -> new ResourceNotFoundException("Centro", dto.getCentroDeEsteticaId()))) ;
        resenia.setTurno(turnoRepository.findById(dto.getTurnoId())
                .orElseThrow(()  -> new ResourceNotFoundException("Turno", dto.getTurnoId()))); ;
        return reseniaMapper.toResponseDTO(reseniaRepository.save(resenia));

    }



    @Override
    @Transactional(readOnly = true)
    public List<ReseniaResponseDTO>listarPorCentro(Long centroId){
        return reseniaRepository.findByCentroDeEsteticaId(centroId).stream()
                .map(reseniaMapper::toResponseDTO)
                .collect(Collectors.toList());
    }
    @Override
    @Transactional
    public ReseniaResponseDTO actualizar(Long id, ReseniaDTO dto) {
        Resenia existente = reseniaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reseña", id));

        Resenia actualizado = reseniaMapper.toEntity(dto);
        actualizado.setId(id);
        return reseniaMapper.toResponseDTO(reseniaRepository.save(actualizado));
    }
    @Override
    @Transactional(readOnly = true)
    public List<ReseniaResponseDTO> listarTodas() {
        return reseniaRepository.findAll().stream()
                .map(reseniaMapper::toResponseDTO)
                .toList();
    }
    @Override
    @Transactional(readOnly = true)
    public ReseniaResponseDTO obtenerPorId(Long id) {
        return reseniaRepository.findById(id)
                .map(reseniaMapper::toResponseDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Reseña", id));
    }


}
