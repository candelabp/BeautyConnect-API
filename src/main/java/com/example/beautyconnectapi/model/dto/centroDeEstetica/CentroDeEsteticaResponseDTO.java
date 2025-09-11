package com.example.beautyconnectapi.model.dto.centroDeEstetica;

import com.example.beautyconnectapi.model.dto.domicilio.DomicilioResponseDTO;
import com.example.beautyconnectapi.model.dto.horarioCentro.HorarioCentroResponseDTO;
import com.example.beautyconnectapi.model.dto.profesional.ProfesionalResponseDTO;
import com.example.beautyconnectapi.model.dto.profesional.ProfesionalResponseDTOSimple;
import com.example.beautyconnectapi.model.dto.resenia.ReseniaResponseDTO;
import com.example.beautyconnectapi.model.dto.resenia.ReseniaResponseDTOSimple;
import com.example.beautyconnectapi.model.dto.servicio.ServicioResponseDTO;
import com.example.beautyconnectapi.model.dto.servicio.ServicioResponseDTOSimple;
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

    private Long cuit;

    private Estado estado;

    private DomicilioResponseDTO domicilio;

    private List<ServicioResponseDTOSimple> servicios;

    private List<ReseniaResponseDTOSimple> resenias;

    private List<ProfesionalResponseDTOSimple> profesionales;

    private HorarioCentroResponseDTO horarioCentro;
}
