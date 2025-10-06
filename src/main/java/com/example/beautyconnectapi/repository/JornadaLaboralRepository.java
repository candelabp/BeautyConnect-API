package com.example.beautyconnectapi.repository;

import com.example.beautyconnectapi.model.entity.JornadaLaboral;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.DayOfWeek;
import java.util.List;

public interface JornadaLaboralRepository extends JpaRepository<JornadaLaboral, Long> {
    List<JornadaLaboral> findAllByProfesional_IdAndDiaAndActiveTrue(Long profId, DayOfWeek dia);
    List<JornadaLaboral> findAllByProfesional_Id(Long profesionalId);
    List<JornadaLaboral> findAllByProfesional_IdAndDia(Long profesionalId, DayOfWeek dia);
}