package com.clt.toscana.model.dto;

import lombok.Data;

import java.util.Map;

/**
 * Request DTO for sending email.
 * Supported template names: default-email, notification-email, alert-email.
 */
@Data
public class EmailRequest {
    private String to;
    private String subject;
    private String body;
    private boolean html;
    private String templateName;
    private Map<String, Object> templateVariables;
}
