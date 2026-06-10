package com.clt.toscana.model.dto;

import lombok.Data;

import java.util.Map;

/**
 * Request DTO for sending a push notification.
 */
@Data
public class NotificationRequest {
    private String token;
    private String topic;
    private String title;
    private String body;
    private Map<String, String> data;
}
