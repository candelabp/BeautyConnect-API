package com.example.beautyconnectapi.model.dto.centroDeEstetica;

import com.example.beautyconnectapi.model.dto.domicilio.DomicilioDTO;
import com.example.beautyconnectapi.model.dto.horarioCentro.HorarioCentroDTO;
import jakarta.validation.constraints.NotBlank;
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
public class CentroDeEsteticaDTO {
    @NotBlank(message = "El campo nombre no puede ser null ni estar vacio")
    private String nombre;

    @NotBlank(message = "El campo descripcion no puede ser null ni estar vacio")
    private String descripcion;

    @NotBlank(message = "El campo imagen no puede ser null ni estar vacio")
    private String imagen;

    @NotBlank(message = "El campo docValido no puede ser null ni estar vacio")
    private String docValido;

    @NotNull(message = "El campo cuit no puede ser null")
    @Positive(message = "El campo cuit debe ser positivo")
    private Long cuit;

    @NotNull(message = "El campo prestadorDeServicioId no puede ser null")
    @Positive(message = "El campo prestadorDeServicioId debe ser positivo")
    private Long prestadorDeServicioId;

    private DomicilioDTO domicilio;

    @NotEmpty(message = "El horario centro no puede estar vacio")
    private List<HorarioCentroDTO> horariosCentro;
}
