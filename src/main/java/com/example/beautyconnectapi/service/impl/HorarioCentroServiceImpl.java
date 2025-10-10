package com.example.beautyconnectapi.service.impl;

import com.example.beautyconnectapi.config.mapper.HorarioCentroMapper;
import com.example.beautyconnectapi.model.dto.horarioCentro.HorarioCentroDTO;
import com.example.beautyconnectapi.model.dto.horarioCentro.HorarioCentroResponseDTO;
import com.example.beautyconnectapi.model.entity.Domicilio;
import com.example.beautyconnectapi.model.entity.HorarioCentro;
import com.example.beautyconnectapi.repository.HorarioCentroRepository;
import com.example.beautyconnectapi.service.HorarioCentroService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class HorarioCentroServiceImpl implements HorarioCentroService {
    private final HorarioCentroRepository horarioCentroRepository;
    private final HorarioCentroMapper horarioCentroMapper;

    public HorarioCentroServiceImpl(HorarioCentroRepository horarioCentroRepository,HorarioCentroMapper horarioCentroMapper ){
        this.horarioCentroRepository = horarioCentroRepository;
        this.horarioCentroMapper = horarioCentroMapper;
    }

    @Override
    @Transactional
    public HorarioCentroResponseDTO updateHorarioCentro(HorarioCentroDTO dto, Long horarioId){
        HorarioCentro horarioCentro = horarioCentroRepository.findById(horarioId)
                .orElseThrow(() -> new RuntimeException("Horario no encontrado"));
        if (!horarioCentro.getHoraMInicio().equals(dto.getHoraMInicio())) {
            horarioCentro.setHoraMInicio(dto.getHoraMInicio());
        }

        if (!horarioCentro.getDia().equals(dto.getDia())) {
            horarioCentro.setDia(dto.getDia());
        }

        if (!horarioCentro.getHoraMFinalizacion().equals(dto.getHoraMFinalizacion())) {
            horarioCentro.setHoraMFinalizacion(dto.getHoraMFinalizacion());
        }

        if (!horarioCentro.getHoraTInicio().equals(dto.getHoraTInicio())) {
            horarioCentro.setHoraTInicio(dto.getHoraTInicio());
        }

        if(!horarioCentro.getHoraTFinalizacion().equals(dto.getHoraTFinalizacion())){
            horarioCentro.setHoraTFinalizacion(dto.getHoraTFinalizacion());
        }


        horarioCentroRepository.save(horarioCentro);
        return horarioCentroMapper.toResponseDTO(horarioCentro);
    }

    @Override
    @Transactional
    public HorarioCentroResponseDTO saveHorarioCentro(HorarioCentroDTO dto){

        HorarioCentro horarioCentro = horarioCentroMapper.toEntity(dto);
        horarioCentroRepository.save(horarioCentro);
        return horarioCentroMapper.toResponseDTO(horarioCentro);
    }

}
