package com.example.beautyconnectapi.config.firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Configuration
public class FirebaseConfig {

    @Bean
    public FirebaseApp firebaseApp() throws Exception {
        if (!FirebaseApp.getApps().isEmpty()) {
            return FirebaseApp.getInstance();
        }

        Map<String, String> env = System.getenv();

        String projectId = env.get("FIREBASE_PROJECT_ID");
        String privateKeyId = env.get("FIREBASE_PRIVATE_KEY_ID");
        String privateKey = env.get("FIREBASE_PRIVATE_KEY"); // con \n literales
        String clientEmail = env.get("FIREBASE_CLIENT_EMAIL");
        String clientId = env.get("FIREBASE_CLIENT_ID");

        if (projectId == null || privateKeyId == null || privateKey == null || clientEmail == null || clientId == null) {
            throw new IllegalStateException("Faltan variables de entorno de Firebase.");
        }

        // Convertimos \n literales a saltos reales
        String fixedPrivateKey = privateKey.replace("\\n", "\n");

        String json = """
            {
              "type": "service_account",
              "project_id": "%s",
              "private_key_id": "%s",
              "private_key": "%s",
              "client_email": "%s",
              "client_id": "%s",
              "auth_uri": "https://accounts.google.com/o/oauth2/auth",
              "token_uri": "https://oauth2.googleapis.com/token",
              "auth_provider_x509_cert_url": "https://www.googleapis.com/oauth2/v1/certs",
              "client_x509_cert_url": "https://www.googleapis.com/robot/v1/metadata/x509/%s",
              "universe_domain": "googleapis.com"
            }
            """.formatted(
                projectId,
                privateKeyId,
                fixedPrivateKey,
                clientEmail,
                clientId,
                clientEmail.replace("@", "%40")
        );

        GoogleCredentials credentials = GoogleCredentials.fromStream(
                new ByteArrayInputStream(json.getBytes(StandardCharsets.UTF_8))
        );

        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(credentials)
                .build();

        return FirebaseApp.initializeApp(options);
    }

    @Bean
    public FirebaseAuth firebaseAuth(FirebaseApp app) {
        return FirebaseAuth.getInstance(app);
    }
}