package com.example.beautyconnectapi.service;

import com.example.beautyconnectapi.model.dto.jornadaLaboral.JornadaLaboralCreateDTO;
import com.example.beautyconnectapi.model.dto.jornadaLaboral.JornadaLaboralResponseDTO;
import com.example.beautyconnectapi.model.dto.jornadaLaboral.JornadaLaboralUpdateDTO;

import java.util.List;

public interface JornadaLaboralService {
    JornadaLaboralResponseDTO create(JornadaLaboralCreateDTO dto);
    List<JornadaLaboralResponseDTO> bulkCreate(List<JornadaLaboralCreateDTO> dtos);
    JornadaLaboralResponseDTO get(Long id);
    List<JornadaLaboralResponseDTO> listByProfesional(Long profesionalId);
    List<JornadaLaboralResponseDTO> listByProfesionalAndDia(Long profesionalId, String dia); // opcional
    JornadaLaboralResponseDTO update(Long id, JornadaLaboralUpdateDTO dto);
    JornadaLaboralResponseDTO toggleActivo(Long id, boolean activo);
    void delete(Long id);
}
