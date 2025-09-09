package com.example.beautyconnectapi.model.dto.centroDeEstetica;

import com.example.beautyconnectapi.model.dto.domicilio.DomicilioResponseDTO;
import com.example.beautyconnectapi.model.dto.horarioCentro.HorarioCentroResponseDTO;
import com.example.beautyconnectapi.model.dto.profesional.ProfesionalResponseDTO;
import com.example.beautyconnectapi.model.dto.resenia.ReseniaResponseDTO;
import com.example.beautyconnectapi.model.dto.servicio.ServicioResponseDTOSimple;
import com.example.beautyconnectapi.model.enums.Estado;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CentroDeEsteticaResponseDTOSimple {
    private Long id;

    private String nombre;

    private String descripcion;

    private String imagen;

    private String docValido;

    private Long cuit;

    private Estado estado;

    private DomicilioResponseDTO domicilio;

    private HorarioCentroResponseDTO horarioCentro;
}
