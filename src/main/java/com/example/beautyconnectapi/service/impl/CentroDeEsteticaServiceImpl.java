package com.example.beautyconnectapi.service.impl;

import com.example.beautyconnectapi.config.mapper.CentroDeEsteticaMapper;
import com.example.beautyconnectapi.model.dto.centroDeEstetica.CentroDeEsteticaDTO;
import com.example.beautyconnectapi.model.dto.centroDeEstetica.CentroDeEsteticaResponseDTO;
import com.example.beautyconnectapi.model.entity.CentroDeEstetica;
import com.example.beautyconnectapi.model.enums.Estado;
import com.example.beautyconnectapi.repository.CentroDeEsteticaRepository;
import com.example.beautyconnectapi.repository.DomicilioRepository;
import com.example.beautyconnectapi.repository.PrestadorDeServicioRepository;
import com.example.beautyconnectapi.service.CentroDeEsteticaService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CentroDeEsteticaServiceImpl implements CentroDeEsteticaService {
    private final CentroDeEsteticaRepository centroDeEsteticaRepository;
    private final CentroDeEsteticaMapper centroDeEsteticaMapper;
    private final PrestadorDeServicioRepository prestadorDeServicioRepository;

    public CentroDeEsteticaServiceImpl(CentroDeEsteticaRepository centroDeEsteticaRepository, CentroDeEsteticaMapper centroDeEsteticaMapper, PrestadorDeServicioRepository prestadorDeServicioRepository) {
        this.centroDeEsteticaRepository = centroDeEsteticaRepository;
        this.centroDeEsteticaMapper = centroDeEsteticaMapper;
        this.prestadorDeServicioRepository = prestadorDeServicioRepository;
    }

    @Override
    @Transactional
    public CentroDeEsteticaResponseDTO registrar(CentroDeEsteticaDTO centroDeEsteticadto) {
        CentroDeEstetica centroDeEstetica = centroDeEsteticaMapper.toEntity(centroDeEsteticadto);
        centroDeEstetica.setEstado(Estado.PENDIENTE);
//        centroDeEstetica.setActive(false);
        centroDeEstetica.setPrestadorDeServicio(prestadorDeServicioRepository.findById(centroDeEsteticadto.getPrestadorDeServicioId())
                .orElseThrow(()  -> new RuntimeException("Prestador no encontrado"))) ;
        return centroDeEsteticaMapper.toResponseDTO(centroDeEsteticaRepository.save(centroDeEstetica));
    }

    @Override
    @Transactional(readOnly = true)
    public List<CentroDeEsteticaResponseDTO> listar() {
        return centroDeEsteticaRepository.findAll().stream()
                .map(centroDeEsteticaMapper::toResponseDTO)
                .collect(Collectors.toList());

    }

    @Override
    @Transactional(readOnly = true)
    public List<CentroDeEsteticaResponseDTO> listarPorEstado(Estado estado) {
        return centroDeEsteticaRepository.findByEstado(estado).stream()
                .map(centroDeEsteticaMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<CentroDeEsteticaResponseDTO> listarPorEstadoyActive(Estado estado, boolean active) {
        return centroDeEsteticaRepository.findByEstadoAndActive(estado, active).stream()
                .map(centroDeEsteticaMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public CentroDeEsteticaResponseDTO cambiarEstado(Long id, Estado estado) {
        CentroDeEstetica centroDeEstetica = centroDeEsteticaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Centro no encontrado"));
        centroDeEstetica.setEstado(estado);
//        if (estado.equals(Estado.ACEPTADO)) {
//            centroDeEstetica.setActive(true);
//        }
        if (estado.equals(Estado.RECHAZADO)) {
            centroDeEstetica.setActive(false);
        }
        return centroDeEsteticaMapper.toResponseDTO(centroDeEstetica);
    }

    @Override
    @Transactional
    public CentroDeEsteticaResponseDTO actualizar(Long id, CentroDeEsteticaDTO dto) {
        CentroDeEstetica entity = centroDeEsteticaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Centro no encontrado"));
        if ((!entity.getNombre().equals(dto.getNombre()) && (!dto.getNombre().isBlank()))) {
            entity.setNombre(dto.getNombre());
        }
        if((!entity.getCuit().equals(dto.getCuit()) && (!dto.getNombre().isBlank()))){
            entity.setCuit(dto.getCuit());
        }
        if((!entity.getDescripcion().equals(dto.getDescripcion()) && (!dto.getDescripcion().isBlank()))){
            entity.setDescripcion(dto.getDescripcion());
        }
        if((!entity.getImagen().equals(dto.getImagen()) && (dto.getImagen().isBlank()))){
            entity.setImagen(dto.getImagen());
        }

        return centroDeEsteticaMapper.toResponseDTO(entity);
    }


    @Override
    @Transactional(readOnly = true)
    public CentroDeEsteticaResponseDTO obtenerPorId(Long id) {
        return centroDeEsteticaRepository.findById(id)
                .map(centroDeEsteticaMapper::toResponseDTO)
                .orElseThrow(() -> new EntityNotFoundException("Centro no encontrado"));
    }

    @Override
    @Transactional(readOnly = true)
    public Long obtenerIdPorUid(String uid) {
        Long id = centroDeEsteticaRepository.findIdByUsuarioUid(uid);
        if (id == null) {
            throw new RuntimeException("No se encontró Centro de Estética para el uid " + uid);
        }
        return id;
    }

    @Override
    @Transactional(readOnly = true)
    public CentroDeEsteticaResponseDTO obtenerPorPrestador(Long idPrestador) {
        CentroDeEstetica centroDeEstetica = centroDeEsteticaRepository.findByPrestadorDeServicioId(idPrestador);

        return centroDeEsteticaMapper.toResponseDTO(centroDeEstetica);


    }

    @Override
    @Transactional
    public CentroDeEsteticaResponseDTO activar_desactivar(Long id){
        CentroDeEstetica centroDeEstetica = centroDeEsteticaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Centro no encontrado"));

        centroDeEstetica.setActive(!centroDeEstetica.getActive());
        return centroDeEsteticaMapper.toResponseDTO(centroDeEstetica);
    }
}
