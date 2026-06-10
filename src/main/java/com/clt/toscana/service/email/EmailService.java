package com.clt.toscana.service.email;

import com.clt.toscana.model.dto.EmailRequest;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

/**
 * Service for sending emails via SMTP.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;

    /**
     * Sends an email (plain text or HTML, with optional Thymeleaf template).
     */
    public void sendEmail(EmailRequest request) throws MessagingException {
        log.info("Sending email to: {}", request.getTo());
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        helper.setTo(request.getTo());
        helper.setSubject(request.getSubject());

        if (request.getTemplateName() != null && !request.getTemplateName().isBlank()) {
            Context ctx = new Context();
            if (request.getTemplateVariables() != null) {
                request.getTemplateVariables().forEach(ctx::setVariable);
            }
            String html = templateEngine.process(resolveTemplateName(request.getTemplateName()), ctx);
            helper.setText(html, true);
        } else {
            helper.setText(request.getBody(), request.isHtml());
        }

        mailSender.send(message);
        log.info("Email sent to: {}", request.getTo());
    }

    private String resolveTemplateName(String templateName) {
        return switch (templateName) {
            case "default-email" -> "mail/default-email";
            case "notification-email" -> "mail/notification-email";
            case "alert-email" -> "mail/alert-email";
            default -> throw new IllegalArgumentException("Unsupported email template: " + templateName);
        };
    }
}
