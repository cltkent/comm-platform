package com.clt.toscana.service.event;

import org.springframework.context.ApplicationEvent;

import java.util.Map;

/**
 * Internal Spring application event for event distribution.
 */
public class CommEvent extends ApplicationEvent {
    private final String topic;
    private final String routingKey;
    private final Map<String, Object> payload;

    public CommEvent(Object source, String topic, String routingKey, Map<String, Object> payload) {
        super(source);
        this.topic = topic;
        this.routingKey = routingKey;
        this.payload = payload;
    }

    public String getTopic() {
        return topic;
    }

    public String getRoutingKey() {
        return routingKey;
    }

    public Map<String, Object> getPayload() {
        return payload;
    }
}
