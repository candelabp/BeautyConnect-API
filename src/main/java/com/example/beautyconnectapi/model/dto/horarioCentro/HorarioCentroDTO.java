package com.example.beautyconnectapi.model.dto.horarioCentro;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.firebase.database.annotations.NotNull;
import lombok.*;

import java.time.DayOfWeek;
import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class HorarioCentroDTO {
    private DayOfWeek dia;

    private LocalTime horaMInicio;

    private LocalTime horaMFinalizacion;

    private LocalTime horaTInicio;

    private LocalTime horaTFinalizacion;

}
