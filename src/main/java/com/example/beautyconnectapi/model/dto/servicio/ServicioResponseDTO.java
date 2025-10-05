package com.example.beautyconnectapi.model.dto.servicio;

import com.example.beautyconnectapi.model.dto.centroDeEstetica.CentroDeEsteticaResponseDTO;
import com.example.beautyconnectapi.model.enums.TipoDeServicio;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ServicioResponseDTO {
    private Long id;
    private Boolean active;
    private TipoDeServicio tipoDeServicio;
    private String descripcion;
    private String titulo;
    private Double precio;

    private CentroDeEsteticaResponseDTO centroDeEstetica;
}
