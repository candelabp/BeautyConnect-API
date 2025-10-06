package com.example.beautyconnectapi.model.dto.turno;

import com.example.beautyconnectapi.model.dto.centroDeEstetica.CentroDeEsteticaResponseDTO;
import com.example.beautyconnectapi.model.dto.cliente.ClienteResponseDTO;
import com.example.beautyconnectapi.model.dto.profesionalServicio.ProfesionalServicioResponseDTO;
import com.example.beautyconnectapi.model.enums.EstadoTurno;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class TurnoResponseDTO {
    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fecha;

    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime hora;

    private EstadoTurno estado;

    private ClienteResponseDTO cliente;

    private ProfesionalServicioResponseDTO profesionalServicio;

    private CentroDeEsteticaResponseDTO centroDeEstetica;

}
