package com.example.beautyconnectapi.model.dto.jornadaLaboral;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.time.DayOfWeek;
import java.time.LocalTime;
@Getter @Setter
public class JornadaLaboralCreateDTO {
    @NotNull(message = "El campo profesional no puede ser null")
    @Positive(message = "El campo profesional debe ser positivo")
    private Long profesionalId;

    @NotNull(message = "El dia es obligatorio")
    private DayOfWeek dia;

    @NotNull(message = "La hora de inicio de la tarde es obligatoria")
    private LocalTime horaInicio;

    @NotNull(message = "La hora de fin de la tarde es obligatoria")
    private  LocalTime horaFin;

    @NotNull(message = "El campo activo no puede ser null")
    private Boolean activo;
}
