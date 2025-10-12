package com.example.beautyconnectapi.service.impl;

import com.example.beautyconnectapi.service.EmailService;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.IOException;
import java.util.Map;

    @Service
    public class EmailServiceImpl implements EmailService {

        private final TemplateEngine templateEngine;
        private final String apiKey;
        private final String from;

        public EmailServiceImpl(
                TemplateEngine templateEngine,
                @Value("${SENDGRID_API_KEY}") String apiKey,
                @Value("${MAIL_FROM}") String from
        ) {
            this.templateEngine = templateEngine;
            this.apiKey = apiKey;
            this.from = from;
        }

        @Override
        @Transactional
        @Async
        public void enviarMailConTemplate(String destinatario, String asunto, String template, Map<String, Object> variables) {
            try {
                // Generar HTML con Thymeleaf
                Context context = new Context();
                context.setVariables(variables);
                String html = templateEngine.process(template, context);

                // Preparar email con SendGrid
                Email fromEmail = new Email(from, "BeautyConnect");
                Email toEmail = new Email(destinatario);
                Content content = new Content("text/html", html);
                Mail mail = new Mail(fromEmail, asunto, toEmail, content);

                // Enviar
                SendGrid sg = new SendGrid(apiKey);
                Request request = new Request();
                request.setMethod(Method.POST);
                request.setEndpoint("mail/send");
                request.setBody(mail.build());

                Response response = sg.api(request);
                if (response.getStatusCode() >= 400) {
                    throw new RuntimeException("Error enviando mail: " + response.getStatusCode() + " - " + response.getBody());
                }

                System.out.println("✅ Mail enviado a " + destinatario + " vía SendGrid");

            } catch (IOException e) {
                throw new RuntimeException("Error enviando mail con SendGrid", e);
            }
        }
    }
