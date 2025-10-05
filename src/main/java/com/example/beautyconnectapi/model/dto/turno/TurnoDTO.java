package com.example.beautyconnectapi.model.dto.turno;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class TurnoDTO {
    @NotNull(message = "La fecha es obligatoria")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fecha;

    @NotNull(message = "La hora es obligatoria")
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime hora;

    @NotNull(message = "El campo cliente no puede ser null")
    @Positive(message = "El campo cliente debe ser positivo")
    private Long clienteId;

    @NotNull(message = "El campo profesionalServicio no puede ser null")
    @Positive(message = "El campo profesionalServicio debe ser positivo")
    private Long profesionalServicioId;

    @NotNull(message = "El campo centroDeEstetica no puede ser null")
    @Positive(message = "El campo centroDeEstetica debe ser positivo")
    private Long centroId;
}
