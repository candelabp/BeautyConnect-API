package com.example.beautyconnectapi.service;

import java.util.Map;

public interface EmailService {
        void enviarMailConTemplate(String destinatario, String asunto, String template, Map<String, Object> variables);
}
