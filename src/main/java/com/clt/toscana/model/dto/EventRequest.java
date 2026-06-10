package com.clt.toscana.model.dto;

import lombok.Data;

import java.util.Map;

/**
 * Request DTO for publishing an event.
 */
@Data
public class EventRequest {
    private String topic;
    private String routingKey;
    private Map<String, Object> payload;
}
