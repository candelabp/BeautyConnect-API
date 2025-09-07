package com.example.beautyconnectapi.repository;

import com.example.beautyconnectapi.model.entity.HorarioCentro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HorarioCentroRepository extends JpaRepository<HorarioCentro, Long> {
}
