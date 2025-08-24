package com.example.beautyconnectapi.model.dto.profesional;

import com.example.beautyconnectapi.model.dto.centroDeEstetica.CentroDeEsteticaResponseDTO;
import com.example.beautyconnectapi.model.dto.disponibilidad.DisponibilidadResponseDTO;
import com.example.beautyconnectapi.model.dto.servicio.ServicioResponseDTO;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ProfesionalResponseDTO {
    private Long id;

    private String nombre;

    private String apellido;

    private List<DisponibilidadResponseDTO> disponibilidades;

    private List<ServicioResponseDTO> servicios;

}
