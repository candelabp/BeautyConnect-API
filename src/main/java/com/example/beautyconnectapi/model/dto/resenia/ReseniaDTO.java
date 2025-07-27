package com.example.beautyconnectapi.model.dto.resenia;

import com.example.beautyconnectapi.model.dto.centroDeEstetica.CentroDeEsteticaDTO;
import com.example.beautyconnectapi.model.dto.cliente.ClienteDTO;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ReseniaDTO {
    private Integer puntuacion;

    private String comentario;
    private int calificacion;

    private ClienteDTO cliente;

    private Long centroDeEsteticaId;
}
