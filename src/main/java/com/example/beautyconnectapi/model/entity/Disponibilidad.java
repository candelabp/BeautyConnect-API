package com.example.beautyconnectapi.model.entity;

import jakarta.persistence.Entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
@Entity
public class Disponibilidad extends Base{
    private LocalDate fecha;

    private LocalTime horaInicio;

    private LocalTime horaFinalizacion;

    @Builder.Default
    private Boolean disponible = true;
}
