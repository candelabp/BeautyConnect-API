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
    private LocalDate fecha;
    private LocalTime horaInicio;
    private LocalTime horaFinalizacion;
}
