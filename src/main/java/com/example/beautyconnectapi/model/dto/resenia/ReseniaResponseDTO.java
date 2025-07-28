package com.example.beautyconnectapi.model.dto.resenia;

import com.example.beautyconnectapi.model.dto.centroDeEstetica.CentroDeEsteticaResponseDTO;
import com.example.beautyconnectapi.model.dto.cliente.ClienteResponseDTO;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ReseniaResponseDTO {
    private Integer puntuacion;

    private String comentario;
    private int calificacion;


    private ClienteResponseDTO cliente;

    private CentroDeEsteticaResponseDTO centroDeEsteticaResponseDTO;
}
