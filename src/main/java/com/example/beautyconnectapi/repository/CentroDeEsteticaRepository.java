package com.example.beautyconnectapi.repository;

import com.example.beautyconnectapi.model.entity.CentroDeEstetica;
import com.example.beautyconnectapi.model.enums.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CentroDeEsteticaRepository extends JpaRepository<CentroDeEstetica,Long> {
    List<CentroDeEstetica> findByEstado(Estado estado);

    @Query("""
        select c.id
        from CentroDeEstetica c
        join c.prestadorDeServicio p
        join p.usuario u
        where u.uid = :uid
    """)
    Long findIdByUsuarioUid(@Param("uid") String uid);

    CentroDeEstetica findByPrestadorDeServicioId(Long prestadorId);

    List<CentroDeEstetica> findByEstadoAndActive(Estado estado, Boolean active);
}

