package com.example.beautyconnectapi.service.impl;

import com.example.beautyconnectapi.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Map;

    @Service
    public class EmailServiceImpl implements EmailService {

        private final JavaMailSender mailSender;
        private final TemplateEngine templateEngine; // Thymeleaf

        public EmailServiceImpl(JavaMailSender mailSender, TemplateEngine templateEngine) {
            this.mailSender = mailSender;
            this.templateEngine = templateEngine;
        }


        @Override
        @Transactional
        @Async
        public void enviarMailConTemplate(String destinatario, String asunto, String template, Map<String, Object> variables) {
            Context context = new Context();
            context.setVariables(variables);
            String html = templateEngine.process(template, context);

            MimeMessage mimeMessage = mailSender.createMimeMessage();
            try {
                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
                helper.setTo(destinatario);
                helper.setSubject(asunto);
                helper.setText(html, true); // true = HTML
                mailSender.send(mimeMessage);
            } catch (MessagingException e) {
                throw new RuntimeException("Error enviando mail con template", e);
            }
        }

//        @Async
//        @Override
//        @Transactional
//        public void enviarMailTurnoConfirmado(String destinatario, Map<String, Object> variables) {
//            try {
//                Context context = new Context();
//                context.setVariables(variables);
//
//                String htmlContent = templateEngine.process("email/turno-confirmado", context);
//
//                MimeMessage message = mailSender.createMimeMessage();
//                MimeMessageHelper helper = new MimeMessageHelper(message, true);
//                helper.setTo(destinatario);
//                helper.setSubject("Tu turno fue confirmado 💅");
//                helper.setText(htmlContent, true);
//
//                mailSender.send(message);
//                System.out.println("✅ Mail de confirmación enviado a " + destinatario);
//            } catch (MessagingException e) {
//                e.printStackTrace();
//            }
//        }
    }


