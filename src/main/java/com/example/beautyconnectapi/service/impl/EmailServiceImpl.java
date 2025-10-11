package com.example.beautyconnectapi.service.impl;

import com.example.beautyconnectapi.service.EmailService;
import com.resend.Resend;
import com.resend.core.exception.ResendException;
import com.resend.services.emails.model.CreateEmailOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.List;
import java.util.Map;

    @Service
    public class EmailServiceImpl implements EmailService {

        private final TemplateEngine templateEngine;
        private final Resend resend;
        private final String from;

        public EmailServiceImpl(
                TemplateEngine templateEngine,
                @Value("${RESEND_API_KEY}") String apiKey,
                @Value("${SPRING_MAIL_USER}") String from
        ) {
            this.templateEngine = templateEngine;
            this.resend = new Resend(apiKey);
            this.from = from;
        }

        @Override
        @Transactional
        @Async
        public void enviarMailConTemplate(String destinatario, String asunto, String template, Map<String, Object> variables) {
            try {
                // 1️⃣ Generar el HTML con Thymeleaf (no lo tocás)
                Context context = new Context();
                context.setVariables(variables);
                String html = templateEngine.process(template, context);

                // 2️⃣ Enviar el mail usando Resend (en vez de JavaMailSender)
                CreateEmailOptions params = CreateEmailOptions.builder()
                        .from("BeautyConnect <" + from + ">")
                        .to(List.of(destinatario))
                        .subject(asunto)
                        .html(html)
                        .build();

                resend.emails().send(params);

                System.out.println("✅ Mail enviado vía Resend a " + destinatario);

            } catch (ResendException e) {
                throw new RuntimeException("Error enviando mail con Resend", e);
            }
        }
    }
