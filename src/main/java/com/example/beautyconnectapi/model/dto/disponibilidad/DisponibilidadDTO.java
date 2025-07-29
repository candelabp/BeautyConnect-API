package com.example.beautyconnectapi.model.dto.disponibilidad;

import lombok.*;

import java.time.DayOfWeek;
import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class DisponibilidadDTO {
    private DayOfWeek dia;
    private LocalTime horaInicio;
    private LocalTime horaFinalizacion;
}
