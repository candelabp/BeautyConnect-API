package com.example.beautyconnectapi.service.impl;

import com.example.beautyconnectapi.config.mapper.ReseniaMapper;
import com.example.beautyconnectapi.model.dto.resenia.ReseniaDTO;
import com.example.beautyconnectapi.model.dto.resenia.ReseniaResponseDTO;
import com.example.beautyconnectapi.model.entity.Resenia;
import com.example.beautyconnectapi.repository.ReseniaRepository;
import com.example.beautyconnectapi.service.ReseniaService;
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

}
