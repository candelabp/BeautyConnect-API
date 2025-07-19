package com.example.beautyconnectapi.repository;

import com.example.beautyconnectapi.model.dto.domicilio.DomicilioResponseDTO;
import com.example.beautyconnectapi.model.entity.Domicilio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DomicilioRepository extends JpaRepository<Domicilio, Long> {
    List<Domicilio> getByCentroDeEsteticaId(Long centroDeEsteticaId);
}
