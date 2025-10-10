package com.example.beautyconnectapi.repository;

import com.example.beautyconnectapi.model.entity.HorarioCentro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HorarioCentroRepository extends JpaRepository<HorarioCentro, Long> {
    List<HorarioCentro> findByCentroDeEsteticaId(Long centroId);

    @Modifying
    @Query("DELETE FROM HorarioCentro h WHERE h.centroDeEstetica.id = :centroId")
    void deleteByCentroDeEsteticaId(@Param("centroId") Long centroId);

}
