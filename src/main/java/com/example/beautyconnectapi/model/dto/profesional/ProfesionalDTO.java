package com.example.beautyconnectapi.model.dto.profesional;

import com.example.beautyconnectapi.model.dto.centroDeEstetica.CentroDeEsteticaDTO;
import com.example.beautyconnectapi.model.dto.disponibilidad.DisponibilidadDTO;
import com.example.beautyconnectapi.model.dto.servicio.ServicioDTO;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ProfesionalDTO {
    private Long id;

    private String nombre;

    private String apellido;

    private List<DisponibilidadDTO> disponibilidades;

    private List<ServicioDTO> servicios;

    private CentroDeEsteticaDTO centroDeEstetica;
}
