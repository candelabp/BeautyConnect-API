package com.example.beautyconnectapi.model.dto.disponibilidad;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class DisponibilidadDTO {
    @NotNull(message = "La fecha es obligatoria")
    private LocalDate fecha;

    @NotNull(message = "La hora inicio es obligatoria")
    private LocalTime horaInicio;

    @NotNull(message = "La hora finalizacion es obligatoria")
    private LocalTime horaFinalizacion;
}
