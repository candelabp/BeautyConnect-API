package com.example.beautyconnectapi.model.dto.profesionalServicio;


import com.example.beautyconnectapi.model.dto.disponibilidad.DisponibilidadDTO;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ProfesionalServicioDTO {
    private Long id;

    @NotNull(message = "El campo duracion no puede ser null")
    @Positive(message = "El campo duracion debe ser positivo")
    private Integer duracion;

    @NotNull(message = "El campo servicio no puede ser null")
    @Positive(message = "El campo servicio debe ser positivo")
    private Long servicioId;

    @NotNull(message = "El campo profesional no puede ser null")
    @Positive(message = "El campo profesional debe ser positivo")
    private Long profesionalId;

    @NotNull(message = "El campo disponibilidad no puede ser null")
    @NotEmpty(message = "La disponibiliada no puede estar vacia")
    private List<DisponibilidadDTO> disponibilidades;
}
