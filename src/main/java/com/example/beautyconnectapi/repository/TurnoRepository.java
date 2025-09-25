package com.example.beautyconnectapi.repository;

import com.example.beautyconnectapi.model.entity.Turno;
import com.example.beautyconnectapi.model.enums.EstadoTurno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TurnoRepository extends JpaRepository<Turno, Long> {
    List<Turno> findByClienteId(Long clienteId);
//    List<Turno> findByPrestadorId(Long prestadorId);
    List<Turno> findAllByProfesionalServicio_Profesional_IdAndFecha(Long profesionalId, LocalDate fecha);
    List<Turno>findByCentroDeEsteticaId(Long centroId);

    @Query("SELECT t FROM Turno t " +
            "WHERE DATE(t.fecha) = :fecha " +
            "AND t.centroDeEstetica.id = :centroId " +
            "AND t.estado = 'PENDIENTE'")
    List<Turno> findAllByFecha(@Param("fecha") LocalDate fecha,
                                         @Param("centroId") Long centroId);


    @Query("SELECT t FROM Turno t " +
            "WHERE t.fecha BETWEEN :inicio AND :fin " +
            "AND t.centroDeEstetica.id = :centroId " +
            "AND t.estado = 'PENDIENTE'")
    List<Turno> findAllByRango(@Param("inicio") LocalDate inicio,
                                         @Param("fin") LocalDate fin,
                                         @Param("centroId") Long centroId);


}
