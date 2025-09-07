package com.example.beautyconnectapi.service.impl;

import com.example.beautyconnectapi.config.mapper.DisponibilidadMapper;
import com.example.beautyconnectapi.config.mapper.ProfesionalServicioMapper;
import com.example.beautyconnectapi.model.dto.disponibilidad.DisponibilidadResponseDTO;
import com.example.beautyconnectapi.model.dto.profesionalServicio.ProfesionalServicioDTO;
import com.example.beautyconnectapi.model.dto.profesionalServicio.ProfesionalServicioResponseDTO;
import com.example.beautyconnectapi.model.entity.Disponibilidad;
import com.example.beautyconnectapi.model.entity.ProfesionalServicio;
import com.example.beautyconnectapi.repository.ProfesionalServicioRepository;
import com.example.beautyconnectapi.service.ProfesionalServicioService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfesionalServicioServiceImpl implements ProfesionalServicioService {
    private final ProfesionalServicioRepository profesionalServicioRepository;
    private final ProfesionalServicioMapper profesionalServicioMapper;
    private final DisponibilidadMapper disponibilidadMapper;

    public ProfesionalServicioServiceImpl(ProfesionalServicioRepository profesionalServicioRepository, ProfesionalServicioMapper profesionalServicioMapper, DisponibilidadMapper disponibilidadMapper){
        this.profesionalServicioRepository = profesionalServicioRepository;
        this.profesionalServicioMapper = profesionalServicioMapper;
        this.disponibilidadMapper = disponibilidadMapper;
    }

    @Override
    public  ProfesionalServicioResponseDTO saveProfServico(ProfesionalServicioDTO profesionalServicioDto){
        ProfesionalServicio profesionalServicio = profesionalServicioMapper.toEntity(profesionalServicioDto);
        profesionalServicioRepository.save(profesionalServicio);
        return profesionalServicioMapper.toResponseDTO(profesionalServicio);
    }

    @Override
    public ProfesionalServicioResponseDTO getProfServicoById(Long id){
        ProfesionalServicio profesionalServicio = profesionalServicioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ProfesionalServicio no encontrado"));
        return profesionalServicioMapper.toResponseDTO(profesionalServicio);
    }

    @Override
    public ProfesionalServicioResponseDTO updateProfServico(Long id, ProfesionalServicioDTO profesionalServicioDto){
        ProfesionalServicio profesionalServicio = profesionalServicioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ProfesionalServicio no encontrado"));

        if (!profesionalServicio.getDuracion().equals(profesionalServicioDto.getDuracion())){
            profesionalServicio.setDuracion(profesionalServicioDto.getDuracion());
        }
        profesionalServicioRepository.save(profesionalServicio);
        return profesionalServicioMapper.toResponseDTO(profesionalServicio);
    }

    @Override
    public ProfesionalServicioResponseDTO deleteProfServico(Long id){
        ProfesionalServicio profesionalServicio = profesionalServicioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ProfesionalServicio no encontrado"));
        profesionalServicio.setActive(false);
        profesionalServicioRepository.save(profesionalServicio);
        return profesionalServicioMapper.toResponseDTO(profesionalServicio);
    }

    @Override
    public List<DisponibilidadResponseDTO> getFechasDisponibles(Long profId, Long servicioId){
        ProfesionalServicio profesionalServicio = profesionalServicioRepository.findByProfesional_IdAndServicio_Id(profId, servicioId)
                .orElseThrow(() -> new RuntimeException("Profesional no encontrado"));
        return profesionalServicio.getDisponibilidades()
                .stream()
                .filter(Disponibilidad::getDisponible)
                .map(disponibilidadMapper::toResponseDTO)
                .toList();

    }

    @Override
    public List<ProfesionalServicioResponseDTO> getAllByServicioId(Long servicioId){
        return profesionalServicioRepository.findByServicio_Id(servicioId)
                .stream()
                .map(profesionalServicioMapper::toResponseDTO)
                .toList();
    }
}
