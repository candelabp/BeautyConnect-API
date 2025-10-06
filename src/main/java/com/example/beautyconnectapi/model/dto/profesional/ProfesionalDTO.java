package com.example.beautyconnectapi.model.dto.profesional;

import com.example.beautyconnectapi.model.dto.centroDeEstetica.CentroDeEsteticaDTO;
import com.example.beautyconnectapi.model.dto.disponibilidad.DisponibilidadDTO;
import com.example.beautyconnectapi.model.dto.servicio.ServicioDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ProfesionalDTO {
    private Long id;

    @NotNull(message = "El campo activo no puede ser null")
    private Boolean active;

    @NotBlank(message = "El campo nombre no puede ser null ni estar vacio")
    private String nombre;

    @NotBlank(message = "El campo apellido no puede ser null ni estar vacio")
    private String apellido;

    @NotNull(message = "El campo contacto no puede ser null")
    @Positive(message = "El campo contacto debe ser positivo")
    private Long contacto;

    @NotNull(message = "El campo centroDeEstetica no puede ser null")
    @Positive(message = "El campo centroDeEstetica debe ser positivo")
    private Long centroDeEsteticaId;
}
