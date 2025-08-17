package com.example.beautyconnectapi.model.dto.Imagen;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
@Data
public class ImageModel {
    private String name;
    private MultipartFile file;

}
