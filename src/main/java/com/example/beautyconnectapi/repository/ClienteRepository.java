package com.example.beautyconnectapi.repository;

import com.example.beautyconnectapi.model.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Cliente getByUsuarioId(Long id);
}
