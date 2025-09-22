package com.example.beautyconnectapi.model.entity;

import com.example.beautyconnectapi.model.enums.Estado;
import com.example.beautyconnectapi.model.enums.EstadoTurno;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
@Entity
public class Turno extends Base {
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fecha;

    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime hora;

    @Enumerated(EnumType.STRING)
    private EstadoTurno estado;

    @ManyToOne
    @JoinColumn(name = "clienteId")
    private Cliente cliente;

    @ManyToOne
    private ProfesionalServicio profesionalServicio;

    @ManyToOne
    @JoinColumn(name= "centroId")
    private CentroDeEstetica centroDeEstetica;
}
