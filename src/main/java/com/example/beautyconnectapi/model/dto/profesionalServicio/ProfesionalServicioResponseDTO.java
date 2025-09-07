package com.example.beautyconnectapi.model.dto.profesionalServicio;

import com.example.beautyconnectapi.model.dto.disponibilidad.DisponibilidadResponseDTO;
import com.example.beautyconnectapi.model.dto.profesional.ProfesionalResponseDTO;
import com.example.beautyconnectapi.model.dto.servicio.ServicioResponseDTO;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ProfesionalServicioResponseDTO {
    private Long id;

    private Integer duracion;

    private ServicioResponseDTO servicioResponseDTO;

    private ProfesionalResponseDTO profesionalResponseDTO;

    private List<DisponibilidadResponseDTO> disponibilidadResponseDTO;
}
