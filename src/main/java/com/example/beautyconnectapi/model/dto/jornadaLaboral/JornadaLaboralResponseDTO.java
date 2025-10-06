package com.example.beautyconnectapi.model.dto.jornadaLaboral;

import lombok.Getter;
import lombok.Setter;

import java.time.DayOfWeek;
import java.time.LocalTime;
@Getter
@Setter
public class JornadaLaboralResponseDTO {
   private Long id;
    private Long profesionalId;
    private  DayOfWeek dia;
    private  LocalTime horaInicio;
    private  LocalTime horaFin;
    private Boolean active;
}
