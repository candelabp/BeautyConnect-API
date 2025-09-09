package com.example.beautyconnectapi.service.impl;

import com.example.beautyconnectapi.config.mapper.DisponibilidadMapper;
import com.example.beautyconnectapi.config.mapper.ProfesionalServicioMapper;
import com.example.beautyconnectapi.model.dto.disponibilidad.DisponibilidadResponseDTO;
import com.example.beautyconnectapi.model.dto.profesionalServicio.ProfesionalServicioDTO;
import com.example.beautyconnectapi.model.dto.profesionalServicio.ProfesionalServicioResponseDTO;
import com.example.beautyconnectapi.model.entity.Disponibilidad;
import com.example.beautyconnectapi.model.entity.ProfesionalServicio;
import com.example.beautyconnectapi.repository.ProfesionalRepository;
import com.example.beautyconnectapi.repository.ProfesionalServicioRepository;
import com.example.beautyconnectapi.repository.ServicioRepository;
import com.example.beautyconnectapi.service.ProfesionalServicioService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProfesionalServicioServiceImpl implements ProfesionalServicioService {
    private final ProfesionalServicioRepository profesionalServicioRepository;
    private final ProfesionalServicioMapper profesionalServicioMapper;
    private final DisponibilidadMapper disponibilidadMapper;
    private final ProfesionalRepository profesionalRepository;
    private final ServicioRepository servicioRepository;

    public ProfesionalServicioServiceImpl(ProfesionalServicioRepository profesionalServicioRepository,
                                          ProfesionalServicioMapper profesionalServicioMapper,
                                          DisponibilidadMapper disponibilidadMapper,
                                          ProfesionalRepository profesionalRepository,
                                          ServicioRepository servicioRepository){
        this.profesionalServicioRepository = profesionalServicioRepository;
        this.profesionalServicioMapper = profesionalServicioMapper;
        this.disponibilidadMapper = disponibilidadMapper;
        this.profesionalRepository = profesionalRepository;
        this.servicioRepository = servicioRepository;
    }

    @Override
    public  ProfesionalServicioResponseDTO saveProfServico(ProfesionalServicioDTO profesionalServicioDto){
        ProfesionalServicio profesionalServicio = profesionalServicioMapper.toEntity(profesionalServicioDto);

        profesionalServicio.setProfesional(profesionalRepository.findById(profesionalServicioDto.getProfesionalId())
                .orElseThrow(()  -> new RuntimeException("Profesional no encontrado")));

        profesionalServicio.setServicio(servicioRepository.findById(profesionalServicioDto.getServicioId())
                .orElseThrow(()  -> new RuntimeException("Servicio no encontrado")));

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
    @Transactional(readOnly = true)
    public ProfesionalServicioResponseDTO getFechasDisponibles(Long profId, Long servicioId){
        ProfesionalServicio profesionalServicio = profesionalServicioRepository.findByProfesional_IdAndServicio_Id(profId, servicioId)
                .orElseThrow(() -> new RuntimeException("Profesional no encontrado"));
        return profesionalServicioMapper.toResponseDTO(profesionalServicio);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProfesionalServicioResponseDTO> getAllByServicioId(Long servicioId){
        return profesionalServicioRepository.findByServicio_Id(servicioId)
                .stream()
                .map(profesionalServicioMapper::toResponseDTO)
                .toList();
    }
}
