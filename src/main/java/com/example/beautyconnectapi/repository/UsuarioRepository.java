package com.example.beautyconnectapi.repository;

import com.example.beautyconnectapi.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario getUsuarioByMail(String mail);
    Usuario getUsuarioByUid(String uid);
    boolean existsByMail(String mail);
    boolean existsByUid(String uid);
}
