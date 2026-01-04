package com.example.beautyconnectapi.service.impl;

import com.example.beautyconnectapi.config.mapper.ProfesionalMapper;
import com.example.beautyconnectapi.exception.ResourceNotFoundException;
import com.example.beautyconnectapi.model.dto.profesional.ProfesionalDTO;
import com.example.beautyconnectapi.model.dto.profesional.ProfesionalResponseDTO;
import com.example.beautyconnectapi.model.entity.CentroDeEstetica;
import com.example.beautyconnectapi.model.entity.Profesional;
import com.example.beautyconnectapi.repository.CentroDeEsteticaRepository;
import com.example.beautyconnectapi.repository.ProfesionalRepository;
import com.example.beautyconnectapi.service.ProfesionalService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfesionalServiceImpl implements ProfesionalService {
    private final ProfesionalRepository profesionalRepository;
    private final ProfesionalMapper profesionalMapper;

    private final CentroDeEsteticaRepository centroDeEsteticaRepository;

    public ProfesionalServiceImpl(ProfesionalRepository profesionalRepository, ProfesionalMapper profesionalMapper, CentroDeEsteticaRepository centroDeEsteticaRepository) {
        this.profesionalRepository = profesionalRepository;
        this.profesionalMapper = profesionalMapper;
        this.centroDeEsteticaRepository = centroDeEsteticaRepository;
    }

    @Override
    @Transactional
    public ProfesionalResponseDTO saveProfesional(ProfesionalDTO profesionalDto){
        Profesional profesional = profesionalMapper.toEntity(profesionalDto);
        profesionalRepository.save(profesional);
        return profesionalMapper.toResponseDTO(profesional);
    }

    @Override
    @Transactional(readOnly = true)
    public ProfesionalResponseDTO getProfesionalById(Long profesionalId){
        Profesional profesional = profesionalRepository.findById(profesionalId)
                .orElseThrow(() -> new ResourceNotFoundException("Profesional", profesionalId));
        return profesionalMapper.toResponseDTO(profesional);
    }

    @Override
    @Transactional
    public ProfesionalResponseDTO updateProfesional(Long profesionalId, ProfesionalDTO profesionalDto){
        Profesional profesional = profesionalRepository.findById(profesionalId)
                .orElseThrow(() -> new ResourceNotFoundException("Profesional", profesionalId));

        if(!profesional.getNombre().equals(profesionalDto.getNombre())){
            profesional.setNombre(profesionalDto.getNombre());

        }
        if(!profesional.getApellido().equals(profesionalDto.getApellido())){
            profesional.setApellido(profesionalDto.getApellido());
        }
        if(!profesional.getContacto().equals(profesionalDto.getContacto())){
            profesional.setContacto(profesionalDto.getContacto());
        }

        profesionalRepository.save(profesional);
        return profesionalMapper.toResponseDTO(profesional);
    }


    @Override
    @Transactional
    public ProfesionalResponseDTO deleteProfesional(Long profesionalId){
        Profesional profesional = profesionalRepository.findById(profesionalId)
                .orElseThrow(() -> new ResourceNotFoundException("Profesional", profesionalId));
        profesional.setActive(!profesional.getActive());
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

    @Override
    @Transactional(readOnly = true)
    public List<ProfesionalResponseDTO> listarPorUid(String uid) {
        List<Profesional> list = profesionalRepository.findByUsuarioUid(uid);
        List<ProfesionalResponseDTO> out = new ArrayList<>();
        for (Profesional p : list) out.add(profesionalMapper.toResponseDTO(p));
        return out;
    }

    @Override
    @Transactional
    public ProfesionalResponseDTO crear(ProfesionalDTO dto) {
        Profesional entity = profesionalMapper.toEntity(dto);
        CentroDeEstetica centroRef = centroDeEsteticaRepository.findById(dto.getCentroDeEsteticaId()).orElseThrow(()->new RuntimeException("no se encontro el centro de estetica"));
        entity.setCentroDeEstetica(centroRef);

        Profesional saved = profesionalRepository.save(entity);

        return profesionalMapper.toResponseDTO(saved);
    }
}
