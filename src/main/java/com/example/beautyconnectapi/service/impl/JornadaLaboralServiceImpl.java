package com.example.beautyconnectapi.service.impl;

import com.example.beautyconnectapi.model.dto.jornadaLaboral.JornadaLaboralCreateDTO;
import com.example.beautyconnectapi.model.dto.jornadaLaboral.JornadaLaboralResponseDTO;
import com.example.beautyconnectapi.model.dto.jornadaLaboral.JornadaLaboralUpdateDTO;
import com.example.beautyconnectapi.model.entity.JornadaLaboral;
import com.example.beautyconnectapi.model.entity.Profesional;
import com.example.beautyconnectapi.repository.JornadaLaboralRepository;
import com.example.beautyconnectapi.repository.ProfesionalRepository;
import com.example.beautyconnectapi.service.JornadaLaboralService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; // ✅ usar el de Spring

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JornadaLaboralServiceImpl implements JornadaLaboralService {

    private final JornadaLaboralRepository repo;
    private final ProfesionalRepository profesionalRepo;

    // ❌ Eliminar el constructor manual incorrecto

    @Override
    @Transactional
    public JornadaLaboralResponseDTO create(JornadaLaboralCreateDTO dto) {
        Profesional prof = profesionalRepo.findById(dto.getProfesionalId())
                .orElseThrow(() -> new EntityNotFoundException("Profesional no encontrado"));

        validarRango(dto.getHoraInicio(), dto.getHoraFin());
        validarNoSolapado(prof.getId(), dto.getDia(), dto.getHoraInicio(), dto.getHoraFin(), null);

        JornadaLaboral j = new JornadaLaboral();
        j.setProfesional(prof);
        j.setDia(dto.getDia());
        j.setHoraInicio(normalize(dto.getHoraInicio()));
        j.setHoraFin(normalize(dto.getHoraFin()));
        j.setActivo(dto.getActivo() == null ? Boolean.TRUE : dto.getActivo());
        return toDTO(repo.save(j));
    }

    @Override
    @Transactional
    public List<JornadaLaboralResponseDTO> bulkCreate(List<JornadaLaboralCreateDTO> dtos) {
        return dtos.stream().map(this::create).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public JornadaLaboralResponseDTO get(Long id) {
        JornadaLaboral j = repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Jornada no encontrada"));
        return toDTO(j);
    }

    @Override
    @Transactional(readOnly = true)
    public List<JornadaLaboralResponseDTO> listByProfesional(Long profesionalId) {
        List<JornadaLaboral> list = repo.findAllByProfesional_Id(profesionalId);
        list.sort(Comparator.comparing(JornadaLaboral::getDia).thenComparing(JornadaLaboral::getHoraInicio));
        return list.stream().map(this::toDTO).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<JornadaLaboralResponseDTO> listByProfesionalAndDia(Long profesionalId, String dia) {
        DayOfWeek d = DayOfWeek.valueOf(dia);
        return repo.findAllByProfesional_IdAndDia(profesionalId, d)
                .stream()
                .sorted(Comparator.comparing(JornadaLaboral::getHoraInicio))
                .map(this::toDTO)
                .toList();
    }

    @Override
    @Transactional
    public JornadaLaboralResponseDTO update(Long id, JornadaLaboralUpdateDTO dto) {
        JornadaLaboral j = repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Jornada no encontrada"));

        validarRango(dto.getHoraInicio(), dto.getHoraFin());
        validarNoSolapado(j.getProfesional().getId(), dto.getDia(), dto.getHoraInicio(), dto.getHoraFin(), id);

        j.setDia(dto.getDia());
        j.setHoraInicio(normalize(dto.getHoraInicio()));
        j.setHoraFin(normalize(dto.getHoraFin()));
        if (dto.getActivo() != null) j.setActivo(dto.getActivo());
        return toDTO(repo.save(j));
    }

    @Override
    @Transactional
    public JornadaLaboralResponseDTO toggleActivo(Long id, boolean activo) {
        JornadaLaboral j = repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Jornada no encontrada"));
        j.setActivo(activo);
        return toDTO(repo.save(j));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!repo.existsById(id)) throw new EntityNotFoundException("Jornada no encontrada");
        repo.deleteById(id);
    }

    // ==== helpers ====
    private void validarRango(LocalTime ini, LocalTime fin) {
        if (ini == null || fin == null) throw new IllegalArgumentException("Horarios requeridos");
        if (!fin.isAfter(ini)) throw new IllegalArgumentException("horaFin debe ser posterior a horaInicio");
    }

    private void validarNoSolapado(Long profesionalId, DayOfWeek dia, LocalTime ini, LocalTime fin, Long ignoreId) {
        int s = toMin(ini), e = toMin(fin);
        for (JornadaLaboral j : repo.findAllByProfesional_IdAndDia(profesionalId, dia)) {
            if (ignoreId != null && j.getId().equals(ignoreId)) continue;
            int s2 = toMin(j.getHoraInicio()), e2 = toMin(j.getHoraFin());
            boolean overlap = s < e2 && s2 < e; // intersección estricta
            if (overlap) {
                throw new IllegalStateException("Solapamiento con jornada ID=" + j.getId());
            }
        }
    }

    private static int toMin(LocalTime t) { return t.getHour() * 60 + t.getMinute(); }
    private static LocalTime normalize(LocalTime t) { return t.withSecond(0).withNano(0); }

    private JornadaLaboralResponseDTO toDTO(JornadaLaboral j) {
        // Opción A: ResponseDTO tiene constructor con todos los args
       /* return new JornadaLaboralResponseDTO(
                j.getId(),
                j.getProfesional().getId(),
                j.getDia(),
                j.getHoraInicio(),
                j.getHoraFin(),
                j.getActivo() != null ? j.getActivo() : Boolean.TRUE
        );*/

        // Opción B (si tu ResponseDTO es clase con setters):

        JornadaLaboralResponseDTO dto = new JornadaLaboralResponseDTO();
        dto.setId(j.getId());
        dto.setProfesionalId(j.getProfesional().getId());
        dto.setDia(j.getDia());
        dto.setHoraInicio(j.getHoraInicio());
        dto.setHoraFin(j.getHoraFin());
        dto.setActivo(j.getActivo() != null ? j.getActivo() : Boolean.TRUE);
        return dto;

    }
}
