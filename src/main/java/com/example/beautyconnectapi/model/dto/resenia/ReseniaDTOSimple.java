package com.example.beautyconnectapi.model.dto.resenia;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ReseniaDTOSimple {
    private Integer puntuacion;

    private String comentario;

    private Long clienteId;
}
