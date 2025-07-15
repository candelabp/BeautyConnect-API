package com.example.beautyconnectapi.model.dto.servicio;

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

    private Integer duracion;

    private Double precio;
}
