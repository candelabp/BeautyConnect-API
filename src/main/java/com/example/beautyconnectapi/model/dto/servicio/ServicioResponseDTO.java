package com.example.beautyconnectapi.model.dto.servicio;

import com.example.beautyconnectapi.model.dto.centroDeEstetica.CentroDeEsteticaDTO;
import com.example.beautyconnectapi.model.dto.centroDeEstetica.CentroDeEsteticaResponseDTO;
import com.example.beautyconnectapi.model.enums.TipoDeServico;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ServicioResponseDTO {
    private Long id;

    private TipoDeServico tipoDeServico;

    private Double precio;

    private CentroDeEsteticaResponseDTO centroDeEsteticaResponseDTO;
}
