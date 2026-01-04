package com.example.beautyconnectapi.config.firebase;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class FirebaseRoleService {
    private final FirebaseAuth auth;

    public FirebaseRoleService(FirebaseAuth auth) {
        this.auth = auth;
    }

    public void asignarRol(String uid, String rol) throws FirebaseAuthException {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", rol); // "ADMIN", "SUPERADMIN", "CLIENTE"
        auth.setCustomUserClaims(uid, claims);
    }
}
