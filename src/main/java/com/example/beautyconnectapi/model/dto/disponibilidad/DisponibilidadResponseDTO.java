package com.example.beautyconnectapi.model.dto.disponibilidad;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class DisponibilidadResponseDTO {
    private Long id;
    private LocalDate fecha;
    private LocalTime horaInicio;
    private LocalTime horaFinalizacion;
    private Boolean disponible;

}
