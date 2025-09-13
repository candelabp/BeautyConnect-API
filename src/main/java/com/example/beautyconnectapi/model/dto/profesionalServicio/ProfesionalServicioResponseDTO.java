package com.example.beautyconnectapi.model.dto.ProfesionalServicio;

import com.example.beautyconnectapi.model.dto.disponibilidad.DisponibilidadResponseDTO;
import com.example.beautyconnectapi.model.dto.profesional.ProfesionalResponseDTO;
import com.example.beautyconnectapi.model.dto.profesional.ProfesionalResponseDTOSimple;
import com.example.beautyconnectapi.model.dto.servicio.ServicioResponseDTO;
import com.example.beautyconnectapi.model.dto.servicio.ServicioResponseDTOSimple;
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

    private ServicioResponseDTOSimple servicio;

    private ProfesionalResponseDTOSimple profesional;

    private List<DisponibilidadResponseDTO> disponibilidades;
}
