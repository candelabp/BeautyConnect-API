package com.example.beautyconnectapi.model.entity;

import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;

public class BloqueoAgenda  extends Base{
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Profesional profesional;
    private java.time.LocalDate fecha;
    private java.time.LocalTime horaInicio;
    private java.time.LocalTime horaFin;
    private String motivo;
}
