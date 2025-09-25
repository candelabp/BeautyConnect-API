package com.example.beautyconnectapi.repository;

import com.example.beautyconnectapi.model.entity.Profesional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfesionalRepository extends JpaRepository<Profesional, Long> {
    @Query("""
        SELECT pr
        FROM Profesional pr
        JOIN pr.centroDeEstetica c
        JOIN c.prestadorDeServicio p
        JOIN p.usuario u
        WHERE u.uid = :uid
    """)
    List<Profesional> findByUsuarioUid(@Param("uid") String uid);

    List<Profesional> findByCentroDeEsteticaId(Long centroId);
}
