package com.example.beautyconnectapi.repository;

import com.example.beautyconnectapi.model.entity.CentroDeEstetica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CentroDeEsteticaRepository extends JpaRepository<CentroDeEstetica,Long> {
}
