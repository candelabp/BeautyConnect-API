package com.example.beautyconnectapi.config.firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.InputStream;

@Configuration
public class FirebaseConfig {

    @Bean
    public FirebaseApp firebaseApp() throws Exception {
        if (FirebaseApp.getApps().isEmpty()) {
            try (InputStream serviceAccount =
                         getClass().getResourceAsStream("/firebase-service-account.json")) {
                if (serviceAccount == null) {
                    throw new IllegalStateException("No se encontró /firebase-service-account.json en resources");
                }
                FirebaseOptions options = FirebaseOptions.builder()
                        .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                        .build();
                return FirebaseApp.initializeApp(options);
            }
        }
        return FirebaseApp.getInstance();
    }

    @Bean
    public FirebaseAuth firebaseAuth(FirebaseApp app) {
        // Garantiza que FirebaseApp está inicializado antes de obtener el Auth
        return FirebaseAuth.getInstance(app);
    }
}
