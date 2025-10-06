package com.example.beautyconnectapi.model.dto.servicio;

import com.example.beautyconnectapi.model.dto.centroDeEstetica.CentroDeEsteticaDTO;
import com.example.beautyconnectapi.model.enums.TipoDeServicio;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ServicioDTO {
    @NotNull(message = "El campo tipoDeServicio no puede ser null")
    private TipoDeServicio tipoDeServicio;

//    @NotNull(message = "El campo active no puede ser null")
    private Boolean active;

    @NotNull(message = "El campo precio no puede ser null")
    @Positive(message = "El campo precio debe ser positivo")
    private Double precio;

    @NotBlank(message = "El campo descripcion no puede ser null ni estar vacio")
    private String descripcion;

    @NotBlank(message = "El campo titulo no puede ser null ni estar vacio")
    private String titulo;

    @NotNull(message = "El campo centroDeEstetica no puede ser null")
    @Positive(message = "El campo centroDeEstetica debe ser positivo")
    private Long centroDeEsteticaId;
}
