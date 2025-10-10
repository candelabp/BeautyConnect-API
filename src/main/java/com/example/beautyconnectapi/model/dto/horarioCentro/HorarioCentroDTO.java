package com.example.beautyconnectapi.model.dto.horarioCentro;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.DayOfWeek;
import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class HorarioCentroDTO {
    private Long id;

    @NotNull(message = "El día desde es obligatorio")
    private DayOfWeek dia;

    @NotNull(message = "La hora de inicio de la mañana es obligatoria")
    private LocalTime horaMInicio;

    @NotNull(message = "La hora de finalizacion de la mañana es obligatoria")
    private LocalTime horaMFinalizacion;

    @NotNull(message = "La hora de inicio de la tarde es obligatoria")
    private LocalTime horaTInicio;

    @NotNull(message = "La hora de finalizacion de la tarde es obligatoria")
    private LocalTime horaTFinalizacion;

}
