package com.example.beautyconnectapi.service.impl;

import com.example.beautyconnectapi.config.mapper.ProfesionalMapper;
import com.example.beautyconnectapi.model.dto.disponibilidad.DisponibilidadDTO;
import com.example.beautyconnectapi.model.dto.profesional.ProfesionalDTO;
import com.example.beautyconnectapi.model.dto.profesional.ProfesionalResponseDTO;
import com.example.beautyconnectapi.model.entity.Disponibilidad;
import com.example.beautyconnectapi.model.entity.Profesional;
import com.example.beautyconnectapi.model.enums.TipoDeServico;
import com.example.beautyconnectapi.repository.DisponibilidadRepository;
import com.example.beautyconnectapi.repository.ProfesionalRepository;
import com.example.beautyconnectapi.service.ProfesionalService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProfesionalServiceImpl implements ProfesionalService {
    private final DisponibilidadRepository disponibilidadRepository;
    private final ProfesionalRepository profesionalRepository;
    private final ProfesionalMapper profesionalMapper;

    public ProfesionalServiceImpl(ProfesionalRepository profesionalRepository, ProfesionalMapper profesionalMapper, DisponibilidadRepository disponibilidadRepository) {
        this.profesionalRepository = profesionalRepository;
        this.profesionalMapper = profesionalMapper;
        this.disponibilidadRepository = disponibilidadRepository;
    }

    @Override
    @Transactional
    public ProfesionalResponseDTO saveProfesional(ProfesionalDTO profesionalDto){
        Profesional profesional = profesionalMapper.toEntity(profesionalDto);
        profesionalRepository.save(profesional);
        return profesionalMapper.toResponseDTO(profesional);
    }

    @Override
    @Transactional
    public ProfesionalResponseDTO getProfesionalById(Long profesionalId){
        Profesional profesional = profesionalRepository.findById(profesionalId)
                .orElseThrow(() -> new RuntimeException("Profesional no encontrado"));
        return profesionalMapper.toResponseDTO(profesional);
    }

    @Override
    @Transactional
    public ProfesionalResponseDTO updateProfesional(Long profesionalId, ProfesionalDTO dto) {
        Profesional e = profesionalRepository.findById(profesionalId)
                .orElseThrow(() -> new RuntimeException("Profesional no encontrado"));

        if (dto.getNombre() != null)   e.setNombre(dto.getNombre());
        if (dto.getApellido() != null) e.setApellido(dto.getApellido());

        e.getServicios().clear();
        if (dto.getServicios() != null) {
            e.getServicios().addAll(dto.getServicios()); // si es Set<TipoDeServico> en el DTO

        }


        Map<Long, Disponibilidad> actualesPorId = e.getDisponibilidades().stream()
                .filter(d -> d.getId() != null)
                .collect(Collectors.toMap(Disponibilidad::getId, d -> d));

        List<Disponibilidad> resultado = new ArrayList<>();

        if (dto.getDisponibilidades() != null) {
            for (DisponibilidadDTO dDto : dto.getDisponibilidades()) {
                if (dDto.getId() != null && actualesPorId.containsKey(dDto.getId())) {
                    // EDITAR existente
                    Disponibilidad existente = actualesPorId.remove(dDto.getId());
                    existente.setDia(dDto.getDia());
                    existente.setHoraInicio(dDto.getHoraInicio());
                    existente.setHoraFinalizacion(dDto.getHoraFinalizacion());
                    resultado.add(existente);
                } else {
                    // CREAR nueva
                    Disponibilidad nueva = Disponibilidad.builder()
                            .dia(dDto.getDia())
                            .horaInicio(dDto.getHoraInicio())
                            .horaFinalizacion(dDto.getHoraFinalizacion())
                            .build();
                    resultado.add(nueva);
                }
            }
        }


        disponibilidadRepository.deleteAll(actualesPorId.values());

        e.getDisponibilidades().clear();
        e.getDisponibilidades().addAll(resultado);

        Profesional saved = profesionalRepository.save(e);
        return profesionalMapper.toResponseDTO(saved);
    }




    @Override
    @Transactional
    public ProfesionalResponseDTO deleteProfesional(Long profesionalId){
        Profesional profesional = profesionalRepository.findById(profesionalId)
                .orElseThrow(() -> new RuntimeException("Profesional no encontrado"));

        profesional.setActive(false);
        profesionalRepository.save(profesional);
        return profesionalMapper.toResponseDTO(profesional);
    }
    @Override
    @Transactional(readOnly = true)
    public List<ProfesionalResponseDTO> getAllProfesionales() {
        List<Profesional> profesionales = profesionalRepository.findAll();
        return profesionales.stream()
                .map(profesionalMapper::toResponseDTO)
                .collect(Collectors.toList());
    }
}
