package com.example.beautyconnectapi.service.impl;

import com.example.beautyconnectapi.config.mapper.PrestadorDeServicioMapper;
import com.example.beautyconnectapi.model.dto.cliente.ClienteResponseDTO;
import com.example.beautyconnectapi.model.dto.prestadorDeServicio.PrestadorDeServicioDTO;
import com.example.beautyconnectapi.model.dto.prestadorDeServicio.PrestadorDeServicioResponseDTO;
import com.example.beautyconnectapi.model.entity.Cliente;
import com.example.beautyconnectapi.model.entity.PrestadorDeServicio;
import com.example.beautyconnectapi.repository.PrestadorDeServicioRepository;
import com.example.beautyconnectapi.service.PrestadorDeServicioService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

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
    public PrestadorDeServicioResponseDTO registrar(PrestadorDeServicioDTO dto) {
        PrestadorDeServicio entity = prestadorDeServicioMapper.toEntity(dto);
        return prestadorDeServicioMapper.toResponseDTO(prestadorDeServicioRepository.save(entity));
    }

    @Override
    public List<PrestadorDeServicioResponseDTO> listarActivos() {
        return prestadorDeServicioRepository.findByActiveTrue().stream()
                .map(prestadorDeServicioMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PrestadorDeServicioResponseDTO buscarPorId(Long id) {
        return prestadorDeServicioRepository.findById(id)
                .map(prestadorDeServicioMapper::toResponseDTO)
                .orElseThrow(() -> new RuntimeException("Prestador no encontrado"));
    }

    @Override
    public PrestadorDeServicioResponseDTO actualizar(Long id, PrestadorDeServicioDTO dto) {
        PrestadorDeServicio entity = prestadorDeServicioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Prestador no encontrado"));

        PrestadorDeServicio actualizado = prestadorDeServicioMapper.toEntity(dto);
        actualizado.setId(id);
        return prestadorDeServicioMapper.toResponseDTO(prestadorDeServicioRepository.save(actualizado));
    }

    @Override
    public PrestadorDeServicioResponseDTO findByUsuarioId(Long idUsuario) {
        PrestadorDeServicio prestadorDeServicio = prestadorDeServicioRepository.getByUsuarioId(idUsuario);
        return prestadorDeServicioMapper.toResponseDTO(prestadorDeServicio);
    }
}
