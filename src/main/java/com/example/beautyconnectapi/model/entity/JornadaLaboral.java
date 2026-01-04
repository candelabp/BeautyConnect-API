package com.example.beautyconnectapi.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.DayOfWeek;
import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
@Entity
public class JornadaLaboral  extends Base{
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Profesional profesional;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DayOfWeek dia;

    @Column(nullable = false)
    private LocalTime horaInicio;

    @Column(nullable = false)
    private LocalTime horaFin;

}
