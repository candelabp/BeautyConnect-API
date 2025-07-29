package com.example.beautyconnectapi.model.dto.centroDeEstetica;

import com.example.beautyconnectapi.model.dto.domicilio.DomicilioDTO;
import com.example.beautyconnectapi.model.dto.resenia.ReseniaDTO;
import com.example.beautyconnectapi.model.dto.servicio.ServicioDTO;
import com.example.beautyconnectapi.model.dto.turno.TurnoDTO;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CentroDeEsteticaDTO {
    private String nombre;

    private String descripcion;

    private String imagen;

    private String docValido;

    private Integer cuit;

    private List<DomicilioDTO> domicilios;

    private List<ServicioDTO> servicios;
}
