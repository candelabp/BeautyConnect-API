package com.example.beautyconnectapi.service;


import com.example.beautyconnectapi.model.dto.HorarioDisponible.HorariosDisponiblesResponse;

import java.time.LocalDate;

public interface DisponibilidadOnDemandService {
    HorariosDisponiblesResponse generarIniciosDisponibles(Long profId, Long servicioId, LocalDate fecha, int stepMin);
}
