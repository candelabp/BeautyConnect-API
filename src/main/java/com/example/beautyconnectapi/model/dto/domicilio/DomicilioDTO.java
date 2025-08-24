package com.example.beautyconnectapi.model.dto.domicilio;

import com.example.beautyconnectapi.model.dto.centroDeEstetica.CentroDeEsteticaDTO;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class DomicilioDTO {
    private String calle;

    private Integer numero;

    private String localidad;

    private Integer codigoPostal;
}
