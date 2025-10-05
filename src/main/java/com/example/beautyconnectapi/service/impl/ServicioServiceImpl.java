package com.example.beautyconnectapi.service.impl;

import com.example.beautyconnectapi.config.mapper.ServicioMapper;
import com.example.beautyconnectapi.model.dto.servicio.ServicioDTO;
import com.example.beautyconnectapi.model.dto.servicio.ServicioResponseDTO;
import com.example.beautyconnectapi.model.entity.CentroDeEstetica;
import com.example.beautyconnectapi.model.entity.Servicio;
import com.example.beautyconnectapi.repository.CentroDeEsteticaRepository;
import com.example.beautyconnectapi.repository.ServicioRepository;
import com.example.beautyconnectapi.service.ServicioService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServicioServiceImpl implements ServicioService {
    private final ServicioRepository servicioRepository;
    private final ServicioMapper servicioMapper;
    private final CentroDeEsteticaRepository centroDeEsteticaRepository;

    public ServicioServiceImpl(ServicioRepository servicioRepository, ServicioMapper servicioMapper,
                               CentroDeEsteticaRepository centroDeEsteticaRepository) {
        this.servicioRepository = servicioRepository;
        this.servicioMapper = servicioMapper;
        this.centroDeEsteticaRepository = centroDeEsteticaRepository;
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
    @Transactional(readOnly = true)
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

        if (!servicio.getPrecio().equals(servicioDto.getPrecio())){
            servicio.setPrecio(servicioDto.getPrecio());
        }

        if (!servicio.getTipoDeServicio().equals(servicioDto.getTipoDeServicio())){
            servicio.setTipoDeServicio(servicioDto.getTipoDeServicio());
        }
        if(!servicio.getDescripcion().equals(servicioDto.getDescripcion())){
            servicio.setDescripcion(servicioDto.getDescripcion());
        }
        if(!servicio.getTitulo().equals(servicioDto.getTitulo())){
            servicio.setTitulo(servicioDto.getTitulo());
        }
        servicioRepository.save(servicio);
        return servicioMapper.toResponseDTO(servicio);
    }

    @Override
    @Transactional
    public ServicioResponseDTO deleteServicio(Long servicioId){
        Servicio servicio = servicioRepository.findById(servicioId)
                .orElseThrow(() -> new RuntimeException("Servicio no encontrado"));
        servicio.setActive(!servicio.getActive());
        servicioRepository.save(servicio);
        return servicioMapper.toResponseDTO(servicio);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ServicioResponseDTO> obtenerServiciosPorCentroId(Long centroDeEsteticaId) {
        List<Servicio> servicios = servicioRepository.getByCentroDeEsteticaId(centroDeEsteticaId);
        return servicios.stream()
                .map(servicioMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ServicioResponseDTO> listarPorUid(String uid) {
        // Si tu repo retorna List<Servicio>, podés usar .stream().toList()
        // Para ser 100% compatible si retorna Iterable, lo hacemos sin streams:
        Iterable<Servicio> servicios = servicioRepository
                .findByCentroDeEstetica_PrestadorDeServicio_Usuario_Uid(uid);

        List<ServicioResponseDTO> result = new ArrayList<>();
        for (Servicio s : servicios) {
            result.add(servicioMapper.toResponseDTO(s));
        }
        return result;
    }

    @Override
    @Transactional
    public ServicioResponseDTO crear(ServicioDTO dto) {
        Servicio entity = servicioMapper.toEntity(dto);


        CentroDeEstetica centroRef = centroDeEsteticaRepository.getReferenceById(dto.getCentroDeEsteticaId());
        entity.setCentroDeEstetica(centroRef);

        Servicio saved = servicioRepository.save(entity);
        return servicioMapper.toResponseDTO(saved);
    }
}
