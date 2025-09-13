package com.example.beautyconnectapi.model.dto.ProfesionalServicio;


import com.example.beautyconnectapi.model.dto.disponibilidad.DisponibilidadDTO;
import com.example.beautyconnectapi.model.dto.profesional.ProfesionalDTO;
import com.example.beautyconnectapi.model.dto.servicio.ServicioDTO;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ProfesionalServicioDTO {
    private Long id;

    private Integer duracion;

    private Long servicioId;

    private Long profesionalId;

    private List<DisponibilidadDTO> disponibilidades;
}
