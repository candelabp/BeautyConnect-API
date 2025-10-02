package com.example.beautyconnectapi.service.impl;

import com.example.beautyconnectapi.config.mapper.PrestadorDeServicioMapper;
import com.example.beautyconnectapi.model.dto.prestadorDeServicio.PrestadorDeServicioDTO;
import com.example.beautyconnectapi.model.dto.prestadorDeServicio.PrestadorDeServicioResponseDTO;
import com.example.beautyconnectapi.model.entity.PrestadorDeServicio;
import com.example.beautyconnectapi.repository.PrestadorDeServicioRepository;
import com.example.beautyconnectapi.service.PrestadorDeServicioService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PrestadorDeServicioImpl implements PrestadorDeServicioService {
    private final PrestadorDeServicioRepository prestadorDeServicioRepository;
    private final PrestadorDeServicioMapper prestadorDeServicioMapper;

    public PrestadorDeServicioImpl(PrestadorDeServicioRepository prestadorDeServicioRepository, PrestadorDeServicioMapper prestadorDeServicioMapper) {
        this.prestadorDeServicioRepository = prestadorDeServicioRepository;
        this.prestadorDeServicioMapper = prestadorDeServicioMapper;
    }

    @Override
    @Transactional
    public PrestadorDeServicioResponseDTO registrar(PrestadorDeServicioDTO dto) {
        PrestadorDeServicio entity = prestadorDeServicioMapper.toEntity(dto);
        entity.setActive(true);
        return prestadorDeServicioMapper.toResponseDTO(prestadorDeServicioRepository.save(entity));
    }

    @Override
    @Transactional(readOnly = true)
    public List<PrestadorDeServicioResponseDTO> getPrestadoresDeServicios() {
        return prestadorDeServicioRepository.findAll().stream()
                .map(prestadorDeServicioMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<PrestadorDeServicioResponseDTO> listarActivos() {
        return prestadorDeServicioRepository.findByActiveTrue().stream()
                .map(prestadorDeServicioMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public PrestadorDeServicioResponseDTO buscarPorId(Long id) {
        return prestadorDeServicioRepository.findById(id)
                .map(prestadorDeServicioMapper::toResponseDTO)
                .orElseThrow(() -> new RuntimeException("Prestador no encontrado"));
    }

    @Override
    @Transactional
    public PrestadorDeServicioResponseDTO actualizar(Long id, PrestadorDeServicioDTO dto) {
        PrestadorDeServicio entity = prestadorDeServicioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Prestador no encontrado"));
        if ((!entity.getNombre().equals(dto.getNombre()) && (!dto.getNombre().isBlank()))) {
            entity.setNombre(dto.getNombre());
        }
        if((!entity.getApellido().equals(dto.getApellido())&& (!dto.getNombre().isBlank()))){
            entity.setApellido(dto.getApellido());
        }
        if((!entity.getTelefono().equals(dto.getTelefono())&& (!dto.getNombre().isBlank()))){
            entity.setTelefono(dto.getTelefono());
        }
        return prestadorDeServicioMapper.toResponseDTO(prestadorDeServicioRepository.save(entity));
    }

    @Override
    @Transactional(readOnly = true)
    public PrestadorDeServicioResponseDTO findByUsuarioId(Long idUsuario) {
        PrestadorDeServicio prestadorDeServicio = prestadorDeServicioRepository.getByUsuarioId(idUsuario);
        return prestadorDeServicioMapper.toResponseDTO(prestadorDeServicio);
    }

   @Override
   @Transactional(readOnly = true)
   public PrestadorDeServicioResponseDTO obtenerPorUid(String uid) {
       PrestadorDeServicio prestadorDeServicio = prestadorDeServicioRepository.findByUsuarioUid(uid)
              .orElseThrow(() -> new RuntimeException("Prestador no encontrado para uid: " + uid));
       return prestadorDeServicioMapper.toResponseDTO(prestadorDeServicio);
    }

    @Override
    @Transactional
    public PrestadorDeServicioResponseDTO cambiarEstadoActive(Long id){
        PrestadorDeServicio prestador = prestadorDeServicioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Prestador no encontrado"));
        prestador.setActive(!prestador.getActive());
        prestador.getUsuario().setActive(prestador.getActive());
        return prestadorDeServicioMapper.toResponseDTO(prestadorDeServicioRepository.save(prestador));
    }
}
