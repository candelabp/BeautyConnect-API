package com.example.beautyconnectapi.model.entity;

import java.time.LocalTime;
import java.util.List;

public class Profesional extends Base{
    private String nombre;

    private LocalTime horarioDisponible;

    private List<Servicio> servicios;
}
