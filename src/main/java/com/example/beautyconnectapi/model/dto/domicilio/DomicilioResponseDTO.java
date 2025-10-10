package com.example.beautyconnectapi.model.dto.domicilio;

import com.example.beautyconnectapi.model.dto.centroDeEstetica.CentroDeEsteticaResponseDTO;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class DomicilioResponseDTO {
    private Long id;

    private String calle;

    private Integer numero;

    private String localidad;

    private Integer codigoPostal;
    private String provincia;
    private Integer latitud;
    private Integer longitud;

}
