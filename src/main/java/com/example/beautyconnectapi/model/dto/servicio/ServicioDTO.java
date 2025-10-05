package com.example.beautyconnectapi.model.dto.servicio;

import com.example.beautyconnectapi.model.dto.centroDeEstetica.CentroDeEsteticaDTO;
import com.example.beautyconnectapi.model.enums.TipoDeServicio;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ServicioDTO {
    private TipoDeServicio tipoDeServicio;
    private Boolean active;

    private Double precio;
    private String descripcion;
    private String titulo;

    private Long centroDeEsteticaId;
}
