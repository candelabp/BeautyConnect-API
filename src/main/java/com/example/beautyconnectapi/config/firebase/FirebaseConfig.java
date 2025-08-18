package com.example.beautyconnectapi.config.firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

@Component
public class FirebaseConfig {

    @PostConstruct
    public void init() throws IOException {
        // Primero chequeamos si ya existe una instancia DEFAULT
        if (FirebaseApp.getApps().isEmpty()) {
            try (InputStream serviceAccount = getClass().getResourceAsStream("/firebase-service-account.json")) {
                if (serviceAccount == null) {
                    throw new FileNotFoundException("No se encontró firebase-service-account.json en resources");
                }
                FirebaseOptions options = FirebaseOptions.builder()
                        .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                        .build();
                FirebaseApp.initializeApp(options);
                System.out.println("FirebaseApp inicializada correctamente");
            }
        } else {
            System.out.println("FirebaseApp ya estaba inicializada, se reutiliza");
        }
    }
}
