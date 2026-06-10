package com.clt.toscana.model.dto;

import lombok.Data;

import java.util.Map;

/**
 * DTO representing an inbound event from the TOS system.
 */
@Data
public class TosInboundEvent {
    private String eventType;
    private String source;
    private Map<String, Object> payload;
    private String timestamp;
}
