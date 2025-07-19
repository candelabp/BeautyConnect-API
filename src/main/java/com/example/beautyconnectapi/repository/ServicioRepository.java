package com.example.beautyconnectapi.repository;

import com.example.beautyconnectapi.model.entity.Servicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServicioRepository extends JpaRepository<Servicio, Long> {
    List<Servicio> getByCentroDeEsteticaId(Long centroDeEsteticaId);
}
