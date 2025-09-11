package com.example.beautyconnectapi.model.dto.horarioCentro;

import lombok.*;

import java.time.DayOfWeek;
import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class HorarioCentroResponseDTO {
    private Long id;
    private DayOfWeek diaDesde;
    private DayOfWeek diaHasta;
    private LocalTime horaInicio;
    private LocalTime horaFinalizacion;
}
