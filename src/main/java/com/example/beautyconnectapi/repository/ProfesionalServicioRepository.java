package com.example.beautyconnectapi.repository;

import com.example.beautyconnectapi.model.entity.ProfesionalServicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfesionalServicioRepository extends JpaRepository<ProfesionalServicio, Long> {
    Optional<ProfesionalServicio> findByProfesional_IdAndServicio_Id(Long profId, Long servicioId);
    List<ProfesionalServicio> findByServicio_Id(Long id);
}
