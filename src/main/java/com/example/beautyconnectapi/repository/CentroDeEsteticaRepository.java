package com.example.beautyconnectapi.repository;

import com.example.beautyconnectapi.model.entity.CentroDeEstetica;
import com.example.beautyconnectapi.model.enums.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CentroDeEsteticaRepository extends JpaRepository<CentroDeEstetica,Long> {
    List<CentroDeEstetica> findByEstado(Estado estado);
    Optional<CentroDeEstetica> findByPrestadoresServicio_Usuario_Uid(String uid);
}
