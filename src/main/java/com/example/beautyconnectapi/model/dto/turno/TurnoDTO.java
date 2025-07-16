package com.example.beautyconnectapi.model.dto.turno;

import com.example.beautyconnectapi.model.dto.cliente.ClienteDTO;
import com.example.beautyconnectapi.model.dto.prestadorDeServicio.PrestadorDeServicioDTO;
import com.example.beautyconnectapi.model.entity.Servicio;
import com.example.beautyconnectapi.model.enums.Estado;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class TurnoDTO {
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fecha;

    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime hora;

    private ClienteDTO cliente;

    private Servicio servicio;

    private PrestadorDeServicioDTO prestadorDeServicioDTO;

    private Estado estado;
}
