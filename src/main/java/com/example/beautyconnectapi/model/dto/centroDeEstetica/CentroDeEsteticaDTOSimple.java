package com.example.beautyconnectapi.model.dto.centroDeEstetica;

import com.example.beautyconnectapi.model.dto.domicilio.DomicilioResponseDTO;
import com.example.beautyconnectapi.model.dto.horarioCentro.HorarioCentroDTO;
import com.example.beautyconnectapi.model.dto.horarioCentro.HorarioCentroResponseDTO;
import com.example.beautyconnectapi.model.enums.Estado;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CentroDeEsteticaDTOSimple {
    private Long id;

    private String nombre;

    private String descripcion;

    private String imagen;

    private String docValido;

    private Long cuit;

    private Long domicilio_id;

    private HorarioCentroDTO horarioCentro;
}
