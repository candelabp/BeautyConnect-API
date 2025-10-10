package com.example.beautyconnectapi.model.dto.domicilio;

import com.example.beautyconnectapi.model.dto.centroDeEstetica.CentroDeEsteticaDTO;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class DomicilioDTO {
    //@NotBlank(message = "El campo calle no puede ser null ni estar vacio")
    private String calle;

    //@NotNull(message = "El campo numero no puede ser null")
    //@Positive(message = "El campo numero debe ser positivo")
    private Integer numero;

    //@NotBlank(message = "El campo localidad no puede ser null ni estar vacio")
    private String localidad;

    //@NotNull(message = "El campo codigoPostal no puede ser null")
    //@Positive(message = "El campo codigoPostal debe ser positivo")
    private Integer codigoPostal;

    //@NotBlank(message = "El campo provincia no puede ser null ni estar vacio")
    private String provincia;

    private Integer latitud;
    private Integer longitud;
}
