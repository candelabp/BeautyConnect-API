package com.example.beautyconnectapi.repository;

import com.example.beautyconnectapi.model.entity.Cliente;
import com.example.beautyconnectapi.model.entity.PrestadorDeServicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PrestadorDeServicioRepository extends JpaRepository<PrestadorDeServicio, Long> {
    List<PrestadorDeServicio> findByActiveTrue();
    PrestadorDeServicio getByUsuarioId(Long id);
    Optional<PrestadorDeServicio> findByUsuarioUid(String uid);

}
