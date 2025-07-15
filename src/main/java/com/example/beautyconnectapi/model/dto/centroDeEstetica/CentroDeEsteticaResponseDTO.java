package com.example.beautyconnectapi.model.dto.centroDeEstetica;

import com.example.beautyconnectapi.model.dto.domicilio.DomicilioResponseDTO;
import com.example.beautyconnectapi.model.dto.prestadorDeServicio.PrestadorDeServicioResponseDTO;
import com.example.beautyconnectapi.model.dto.resenia.ReseniaResponseDTO;
import com.example.beautyconnectapi.model.dto.servicio.ServicioResponseDTO;
import com.example.beautyconnectapi.model.dto.turno.TurnoResponseDTO;
import com.example.beautyconnectapi.model.enums.Estado;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CentroDeEsteticaResponseDTO {
    private Long id;

    private String nombre;

    private String descripcion;

    private String imagen;

    private String docValido;

    private Integer cuit;

    private PrestadorDeServicioResponseDTO prestadoresServicio;

    private List<DomicilioResponseDTO> domicilios;

    private List<ServicioResponseDTO> servicios;

    private List<TurnoResponseDTO> turnos;

    private List<ReseniaResponseDTO> resenias;

    private Estado estado;
}
