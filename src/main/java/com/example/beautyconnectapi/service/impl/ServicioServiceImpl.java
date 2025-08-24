package com.example.beautyconnectapi.service.impl;

import com.example.beautyconnectapi.config.mapper.ServicioMapper;
import com.example.beautyconnectapi.model.dto.servicio.ServicioDTO;
import com.example.beautyconnectapi.model.dto.servicio.ServicioResponseDTO;
import com.example.beautyconnectapi.model.entity.Servicio;
import com.example.beautyconnectapi.repository.ServicioRepository;
import com.example.beautyconnectapi.service.ServicioService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServicioServiceImpl implements ServicioService {
    private final ServicioRepository servicioRepository;
    private final ServicioMapper servicioMapper;

    public ServicioServiceImpl(ServicioRepository servicioRepository, ServicioMapper servicioMapper) {
        this.servicioRepository = servicioRepository;
        this.servicioMapper = servicioMapper;
    }

    @Override
    @Transactional
    public ServicioResponseDTO saveServicio(ServicioDTO servicioDto){
        Servicio servicio = servicioMapper.toEntity(servicioDto);
        servicioRepository.save(servicio);
        return servicioMapper.toResponseDTO(servicio);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ServicioResponseDTO> getAllServicios() {
        List<Servicio> servicios = servicioRepository.findAll();
        return servicios.stream()
                .map(servicioMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ServicioResponseDTO getServicioById(Long servicioId){
        Servicio servicio = servicioRepository.findById(servicioId)
                .orElseThrow(() -> new RuntimeException("Servicio no encontrado"));
        return servicioMapper.toResponseDTO(servicio);
    }

    @Override
    @Transactional
    public ServicioResponseDTO updateServicio(Long servicioId, ServicioDTO servicioDto){
        Servicio servicio = servicioRepository.findById(servicioId)
                .orElseThrow(() -> new RuntimeException("Servicio no encontrado"));

        if (!servicio.getDuracion().equals(servicioDto.getDuracion())){
            servicio.setDuracion(servicioDto.getDuracion());
        }

        if (!servicio.getPrecio().equals(servicioDto.getPrecio())){
            servicio.setPrecio(servicioDto.getPrecio());
        }

        if (!servicio.getTipoDeServico().equals(servicioDto.getTipoDeServico())){
            servicio.setTipoDeServico(servicioDto.getTipoDeServico());
        }

        servicioRepository.save(servicio);
        return servicioMapper.toResponseDTO(servicio);
    }

    @Override
    @Transactional
    public ServicioResponseDTO deleteServicio(Long servicioId){
        Servicio servicio = servicioRepository.findById(servicioId)
                .orElseThrow(() -> new RuntimeException("Servicio no encontrado"));

        servicio.setActive(false);
        servicioRepository.save(servicio);
        return servicioMapper.toResponseDTO(servicio);
    }

    @Override
    @Transactional
    public List<ServicioResponseDTO> obtenerServiciosPorCentroId(Long centroDeEsteticaId) {
        List<Servicio> servicios = servicioRepository.getByCentroDeEsteticaId(centroDeEsteticaId);
        return servicios.stream()
                .map(servicioMapper::toResponseDTO)
                .collect(Collectors.toList());
    }
}
