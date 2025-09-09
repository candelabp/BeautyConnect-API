package com.example.beautyconnectapi.model.dto.servicio;

import com.example.beautyconnectapi.model.enums.TipoDeServicio;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ServicioResponseDTOSimple {
    private Long id;

    private TipoDeServicio tipoDeServicio;

    private Double precio;
}
