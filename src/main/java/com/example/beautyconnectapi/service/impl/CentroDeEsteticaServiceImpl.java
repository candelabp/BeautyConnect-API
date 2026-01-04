package com.example.beautyconnectapi.service.impl;

import com.example.beautyconnectapi.config.mapper.CentroDeEsteticaMapper;
import com.example.beautyconnectapi.exception.BusinessConflictException;
import com.example.beautyconnectapi.exception.ResourceNotFoundException;
import com.example.beautyconnectapi.model.dto.centroDeEstetica.CentroDeEsteticaDTO;
import com.example.beautyconnectapi.model.dto.centroDeEstetica.CentroDeEsteticaResponseDTO;
import com.example.beautyconnectapi.model.entity.CentroDeEstetica;
import com.example.beautyconnectapi.model.entity.HorarioCentro;
import com.example.beautyconnectapi.model.entity.PrestadorDeServicio;
import com.example.beautyconnectapi.model.enums.Estado;
import com.example.beautyconnectapi.repository.*;
import com.example.beautyconnectapi.service.CentroDeEsteticaService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CentroDeEsteticaServiceImpl implements CentroDeEsteticaService {
    private final CentroDeEsteticaRepository centroDeEsteticaRepository;
    private final CentroDeEsteticaMapper centroDeEsteticaMapper;
    private final PrestadorDeServicioRepository prestadorDeServicioRepository;
    private final EmailServiceImpl emailService;
    private final HorarioCentroRepository horarioCentroRepository;
    private final ServicioRepository servicioRepository;
    private final ProfesionalRepository profesionalRepository;
    private final ProfesionalServicioRepository profesionalServicioRepository;

    public CentroDeEsteticaServiceImpl(CentroDeEsteticaRepository centroDeEsteticaRepository, CentroDeEsteticaMapper centroDeEsteticaMapper, PrestadorDeServicioRepository prestadorDeServicioRepository, EmailServiceImpl emailService, HorarioCentroRepository horarioCentroRepository, ServicioRepository servicioRepository, ProfesionalRepository profesionalRepository, ProfesionalServicioRepository profesionalServicioRepository) {
        this.centroDeEsteticaRepository = centroDeEsteticaRepository;
        this.centroDeEsteticaMapper = centroDeEsteticaMapper;
        this.prestadorDeServicioRepository = prestadorDeServicioRepository;
        this.emailService = emailService;
        this.horarioCentroRepository = horarioCentroRepository;
        this.servicioRepository = servicioRepository;
        this.profesionalRepository = profesionalRepository;
        this.profesionalServicioRepository = profesionalServicioRepository;
    }

    @Override
    @Transactional
    public CentroDeEsteticaResponseDTO registrar(CentroDeEsteticaDTO dto) {
        CentroDeEstetica centro = centroDeEsteticaMapper.toEntity(dto);
        centro.setEstado(Estado.PENDIENTE);
        centro.setActive(false);

        PrestadorDeServicio prestador = prestadorDeServicioRepository.findById(dto.getPrestadorDeServicioId())
                .orElseThrow(() -> new ResourceNotFoundException("PrestadorDeServicio", dto.getPrestadorDeServicioId()));

        centro.setPrestadorDeServicio(prestador);

        CentroDeEstetica guardado = centroDeEsteticaRepository.save(centro);
        return centroDeEsteticaMapper.toResponseDTO(guardado);
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
                .orElseThrow(() -> new ResourceNotFoundException("Centro", id));
        centroDeEstetica.setEstado(estado);
        if (estado.equals(Estado.ACEPTADO)) {
            Map<String, Object> variables = new HashMap<>();
            variables.put("nombrePrestador", centroDeEstetica.getPrestadorDeServicio().getNombre());
            variables.put("nombreCentro", centroDeEstetica.getNombre());

            emailService.enviarMailConTemplate(
                    centroDeEstetica.getPrestadorDeServicio().getUsuario().getMail(),
                    "¡Tu centro ha sido aprobado! - BeautyConnect",
                    "email/centroAprobado",
                    variables
            );
        }
        if (estado.equals(Estado.RECHAZADO)) {
            centroDeEstetica.setActive(false);
            Map<String, Object> variables = new HashMap<>();
            variables.put("nombrePrestador", centroDeEstetica.getPrestadorDeServicio().getNombre());
            variables.put("nombreCentro", centroDeEstetica.getNombre());

            emailService.enviarMailConTemplate(
                    centroDeEstetica.getPrestadorDeServicio().getUsuario().getMail(),
                    "Centro rechazado en BeautyConnect",
                    "email/centroRechazado",
                    variables
            );
        }
        return centroDeEsteticaMapper.toResponseDTO(centroDeEstetica);
    }

    @Override
    @Transactional
    public CentroDeEsteticaResponseDTO actualizar(Long id, CentroDeEsteticaDTO dto) {
        CentroDeEstetica entity = centroDeEsteticaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Centro", id));
        if ((!entity.getNombre().equals(dto.getNombre()) && (!dto.getNombre().isBlank()))) {
            entity.setNombre(dto.getNombre());
        }
        if(!entity.getCuit().equals(dto.getCuit() )){
            entity.setCuit(dto.getCuit());
        }
        if((!entity.getDescripcion().equals(dto.getDescripcion()) && (!dto.getDescripcion().isBlank()))){
            entity.setDescripcion(dto.getDescripcion());
        }
        if((!entity.getImagen().equals(dto.getImagen()) && (!dto.getImagen().isBlank()))){
            entity.setImagen(dto.getImagen());
        }
        if((!entity.getDocValido().equals(dto.getDocValido()) && (!dto.getDocValido().isBlank()))){
            entity.setDocValido(dto.getDocValido());
        }

        if (dto.getHorariosCentro() != null && !dto.getHorariosCentro().isEmpty()) {
            horarioCentroRepository.deleteAll(entity.getHorariosCentro());
            entity.getHorariosCentro().clear();

                List<HorarioCentro> nuevosHorarios = dto.getHorariosCentro().stream().map(horarioDTO -> {
                    HorarioCentro horario = new HorarioCentro();
                    horario.setId(horarioDTO.getId());
                    horario.setDia(horarioDTO.getDia());
                    horario.setHoraMInicio(horarioDTO.getHoraMInicio());
                    horario.setHoraMFinalizacion(horarioDTO.getHoraMFinalizacion());
                    horario.setHoraTInicio(horarioDTO.getHoraTInicio());
                    horario.setHoraTFinalizacion(horarioDTO.getHoraTFinalizacion());
                    return horario;
                }).collect(Collectors.toList());
                entity.getHorariosCentro().clear();
                entity.getHorariosCentro().addAll(nuevosHorarios);
        }
        centroDeEsteticaRepository.save(entity);
        return centroDeEsteticaMapper.toResponseDTO(entity);
    }


    @Override
    @Transactional(readOnly = true)
    public CentroDeEsteticaResponseDTO obtenerPorId(Long id) {
        return centroDeEsteticaRepository.findById(id)
                .map(centroDeEsteticaMapper::toResponseDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Centro", id));
    }

    @Override
    @Transactional(readOnly = true)
    public Long obtenerIdPorUid(String uid) {
        Long id = centroDeEsteticaRepository.findIdByUsuarioUid(uid);
        if (id == null) {
            throw new ResourceNotFoundException("Centro de estetica", uid);
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
    public CentroDeEsteticaResponseDTO activar_desactivar(Long id) {

        CentroDeEstetica centro = centroDeEsteticaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("CentroDeEstetica", id));

        if (!centro.getActive()) {
            boolean tieneProfesionales = !profesionalRepository.findByCentroDeEsteticaId(centro.getId()).isEmpty();
            boolean tieneServicios = !servicioRepository.getByCentroDeEsteticaId(centro.getId()).isEmpty();
            if (!tieneProfesionales || !tieneServicios) {
                throw new BusinessConflictException("El centro debe tener al menos un profesional y un servicio antes de activarse.");
            }
            boolean tieneRelacion = centro.getServicios().stream()
                    .anyMatch(servicio -> !profesionalServicioRepository
                            .findByServicio_Id(servicio.getId()).isEmpty()
                    );
            if (!tieneRelacion) {
                throw new BusinessConflictException("No se puede activar el centro: no existe ninguna relación entre profesional y servicio.");
            }
        }
        centro.setActive(!centro.getActive());
        CentroDeEstetica guardado = centroDeEsteticaRepository.save(centro);

        return centroDeEsteticaMapper.toResponseDTO(guardado);
    }


}
