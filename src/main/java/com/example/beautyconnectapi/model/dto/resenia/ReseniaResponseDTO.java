package com.example.beautyconnectapi.model.dto.resenia;

import com.example.beautyconnectapi.model.dto.centroDeEstetica.CentroDeEsteticaResponseDTO;
import com.example.beautyconnectapi.model.dto.cliente.ClienteResponseDTO;
import com.example.beautyconnectapi.model.dto.turno.TurnoResponseDTO;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ReseniaResponseDTO {
    private Long id;

    private Integer puntuacion;

    private String comentario;

    private ClienteResponseDTO cliente;

    private CentroDeEsteticaResponseDTO centroDeEstetica;
    private TurnoResponseDTO turno;
}
