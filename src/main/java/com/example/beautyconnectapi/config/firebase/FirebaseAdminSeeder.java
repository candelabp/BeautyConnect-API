package com.example.beautyconnectapi.config.firebase;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class FirebaseAdminSeeder implements ApplicationRunner {

    private static final List<String> ADMIN_EMAILS = List.of(
            "superadmin-cande@google.com",
            "flor-admin@google.com"
    );

    @Override
    public void run(ApplicationArguments args) throws Exception {
        FirebaseAuth auth = FirebaseAuth.getInstance();

        for (String email : ADMIN_EMAILS) {
            UserRecord user;
            try {
                user = auth.getUserByEmail(email);
            } catch (FirebaseAuthException e) {
                UserRecord.CreateRequest req = new UserRecord.CreateRequest()
                        .setEmail(email)
                        .setPassword(UUID.randomUUID().toString())
                        .setEmailVerified(true);
                user = auth.createUser(req);
            }

            Map<String, Object> claims = new HashMap<>(Optional.ofNullable(user.getCustomClaims()).orElse(Map.of()));
            if (!"SUPERADMIN".equals(claims.get("role"))) {
                claims.put("role", "SUPERADMIN");
                auth.setCustomUserClaims(user.getUid(), claims);
                System.out.println("Asignado role=SUPERADMIN a " + email);
            }
        }
    }
}

