package com.example.beautyconnectapi.service.impl;

import com.example.beautyconnectapi.config.mapper.ReseniaMapper;
import com.example.beautyconnectapi.model.dto.resenia.ReseniaDTO;
import com.example.beautyconnectapi.model.dto.resenia.ReseniaResponseDTO;
import com.example.beautyconnectapi.model.entity.Resenia;
import com.example.beautyconnectapi.repository.ReseniaRepository;
import com.example.beautyconnectapi.service.ReseniaService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReseniaServiceImpl implements ReseniaService {
    private final ReseniaRepository reseniaRepository;
    private final ReseniaMapper reseniaMapper;

    public ReseniaServiceImpl( ReseniaRepository reseniaRepository, ReseniaMapper reseniaMapper){
        this.reseniaMapper = reseniaMapper;
        this.reseniaRepository = reseniaRepository;
    }
    @Override
    public ReseniaResponseDTO crear(ReseniaDTO dto){
        Resenia resenia = reseniaMapper.toEntity(dto);
        return reseniaMapper.toResponseDTO(reseniaRepository.save(resenia));

    }
    @Override
    public List<ReseniaResponseDTO>listarPorCentro(Long centroId){
        return reseniaRepository.findByCentroDeEsteticaId(centroId).stream()
                .map(reseniaMapper::toResponseDTO)
                .collect(Collectors.toList());
    }
    @Override
    public ReseniaResponseDTO actualizar(Long id, ReseniaDTO dto) {
        Resenia existente = reseniaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Reseña no encontrada"));

        Resenia actualizado = reseniaMapper.toEntity(dto);
        actualizado.setId(id);
        return reseniaMapper.toResponseDTO(reseniaRepository.save(actualizado));
    }
    @Override
    public List<ReseniaResponseDTO> listarTodas() {
        return reseniaRepository.findAll().stream()
                .map(reseniaMapper::toResponseDTO)
                .toList();
    }
    @Override
    public ReseniaResponseDTO obtenerPorId(Long id) {
        return reseniaRepository.findById(id)
                .map(reseniaMapper::toResponseDTO)
                .orElseThrow(() -> new EntityNotFoundException("Reseña no encontrada"));
    }


}
