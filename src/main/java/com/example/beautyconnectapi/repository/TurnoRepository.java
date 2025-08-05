package com.example.beautyconnectapi.repository;

import com.example.beautyconnectapi.model.entity.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TurnoRepository extends JpaRepository<Turno, Long> {
    List<Turno> findByClienteId(Long clienteId);
//    List<Turno> findByPrestadorId(Long prestadorId);
}
