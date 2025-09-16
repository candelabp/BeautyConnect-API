package com.example.beautyconnectapi.model.dto.centroDeEstetica;

import com.example.beautyconnectapi.model.dto.domicilio.DomicilioDTO;
import com.example.beautyconnectapi.model.dto.horarioCentro.HorarioCentroDTO;
import com.example.beautyconnectapi.model.dto.profesional.ProfesionalDTOSimple;
import com.example.beautyconnectapi.model.dto.servicio.ServicioDTOSimple;
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

    private DomicilioDTO domicilio;

//    private List<ServicioDTOSimple> servicios;
//
//    private List<ProfesionalDTOSimple> profesionales;

    private List<HorarioCentroDTO> horariosCentro;
}
