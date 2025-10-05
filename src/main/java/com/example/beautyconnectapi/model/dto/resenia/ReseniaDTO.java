package com.example.beautyconnectapi.model.dto.resenia;

import com.example.beautyconnectapi.model.dto.centroDeEstetica.CentroDeEsteticaDTO;
import com.example.beautyconnectapi.model.dto.cliente.ClienteDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ReseniaDTO {
    @NotNull(message = "El campo puntuacion no puede ser null")
    @Positive(message = "El campo puntuacion debe ser positivo")
    private Integer puntuacion;

    @NotBlank(message = "El campo comentario no puede ser null ni estar vacio")
    private String comentario;

    @NotNull(message = "El campo cliente no puede ser null")
    @Positive(message = "El campo cliente debe ser positivo")
    private Long clienteId;

    @NotNull(message = "El campo centroDeEstetica no puede ser null")
    @Positive(message = "El campo centroDeEstetica debe ser positivo")
    private Long centroDeEsteticaId;

    @NotNull(message = "El campo turno no puede ser null")
    @Positive(message = "El campo turno debe ser positivo")
    private Long turnoId;
}
