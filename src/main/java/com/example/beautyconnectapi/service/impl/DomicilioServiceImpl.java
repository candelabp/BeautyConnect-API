package com.example.beautyconnectapi.service.impl;

import com.example.beautyconnectapi.config.mapper.DomicilioMapper;
import com.example.beautyconnectapi.exception.ResourceNotFoundException;
import com.example.beautyconnectapi.model.dto.domicilio.DomicilioDTO;
import com.example.beautyconnectapi.model.dto.domicilio.DomicilioResponseDTO;
import com.example.beautyconnectapi.model.entity.Domicilio;
import com.example.beautyconnectapi.repository.DomicilioRepository;
import com.example.beautyconnectapi.service.DomicilioService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DomicilioServiceImpl implements DomicilioService {

    private final DomicilioRepository domicilioRepository;
    private final DomicilioMapper domicilioMapper;

    public DomicilioServiceImpl(DomicilioRepository domicilioRepository, DomicilioMapper domicilioMapper) {
        this.domicilioRepository = domicilioRepository;
        this.domicilioMapper = domicilioMapper;
    }

    @Override
    @Transactional
    public DomicilioResponseDTO saveDomicilio(DomicilioDTO domicilioDto){
        Domicilio domicilio = domicilioMapper.toEntity(domicilioDto);
        domicilioRepository.save(domicilio);
        return domicilioMapper.toResponseDTO(domicilio);
    }

    @Override
    @Transactional(readOnly = true)
    public DomicilioResponseDTO getDomicilioById(Long domicilioId){
        Domicilio domicilio = domicilioRepository.findById(domicilioId)
                .orElseThrow(() -> new ResourceNotFoundException("Domicilio", domicilioId));
        return domicilioMapper.toResponseDTO(domicilio);
    }

    @Override
    @Transactional
    public DomicilioResponseDTO updateDomicilio(Long domicilioId, DomicilioDTO domicilioDto){
        Domicilio domicilio = domicilioRepository.findById(domicilioId)
                .orElseThrow(() -> new ResourceNotFoundException("Domicilio", domicilioId));

        if (!domicilio.getCalle().equals(domicilioDto.getCalle())) {
            domicilio.setCalle(domicilioDto.getCalle());
        }

        if (!domicilio.getLocalidad().equals(domicilioDto.getLocalidad())) {
            domicilio.setLocalidad(domicilioDto.getLocalidad());
        }

        if (!domicilio.getNumero().equals(domicilioDto.getNumero())) {
            domicilio.setNumero(domicilioDto.getNumero());
        }

        if (!domicilio.getCodigoPostal().equals(domicilioDto.getCodigoPostal())) {
            domicilio.setCodigoPostal(domicilioDto.getCodigoPostal());
        }

        domicilioRepository.save(domicilio);
        return domicilioMapper.toResponseDTO(domicilio);
    }

    @Override
    @Transactional
    public DomicilioResponseDTO deleteDomicilio(Long domicilioId){
        Domicilio domicilio = domicilioRepository.findById(domicilioId)
                .orElseThrow(() -> new ResourceNotFoundException("Domicilio", domicilioId));
        domicilio.setActive(false);
        domicilioRepository.save(domicilio);
        return domicilioMapper.toResponseDTO(domicilio);
    }


}
