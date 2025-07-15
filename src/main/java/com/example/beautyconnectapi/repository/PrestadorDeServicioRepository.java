package com.example.beautyconnectapi.repository;

import com.example.beautyconnectapi.model.entity.PrestadorDeServicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrestadorDeServicioRepository extends JpaRepository<PrestadorDeServicio, Long> {
}
