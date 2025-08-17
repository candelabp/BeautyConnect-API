package com.example.beautyconnectapi.repository;

import com.example.beautyconnectapi.model.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ImageRepository extends JpaRepository<Image, UUID> {

    // Métodos opcionales útiles:
    Optional<Image> findByUrl(String url);

    boolean existsByUrl(String url);

    // Si tu entidad tiene publicId (de Cloudinary), podés agregar:
    Optional<Image> findByPublicId(String publicId);

    void deleteByPublicId(String publicId);
}