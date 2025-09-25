package com.example.beautyconnectapi.model.dto.centroDeEstetica;

import com.example.beautyconnectapi.model.dto.domicilio.DomicilioDTO;
import com.example.beautyconnectapi.model.dto.horarioCentro.HorarioCentroDTO;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CentroDeEsteticaDTO {
//    private Long id;

    private String nombre;

    private String descripcion;

    private String imagen;

    private String docValido;

    private Long cuit;

    private Long prestadorDeServicioId;

    private DomicilioDTO domicilio;

//    private List<ServicioDTOSimple> servicios;
//
//    private List<ProfesionalDTOSimple> profesionales;

    private List<HorarioCentroDTO> horariosCentro;
}
