package com.example.beautyconnectapi.model.dto.servicio;

import com.example.beautyconnectapi.model.dto.centroDeEstetica.CentroDeEsteticaDTO;
import com.example.beautyconnectapi.model.enums.TipoDeServico;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ServicioDTO {
    private TipoDeServico tipoDeServico;

    private Integer duracion;

    private Double precio;

    private CentroDeEsteticaDTO centroDeEsteticaDTO;

}
