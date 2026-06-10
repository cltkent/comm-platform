package com.clt.toscana.service.event;

import com.clt.toscana.broker.MessageBroker;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * Publishes internal communication events to the active external broker when available.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class BrokerEventPublisher {

    private final ObjectProvider<MessageBroker> messageBrokerProvider;
    private final ObjectMapper objectMapper;

    @EventListener
    public void handle(CommEvent event) {
        MessageBroker messageBroker = messageBrokerProvider.getIfAvailable();
        if (messageBroker == null) {
            log.info("No active external broker configured for topic {}", event.getTopic());
            return;
        }

        try {
            String payload = objectMapper.writeValueAsString(event.getPayload());
            messageBroker.publish(event.getTopic(), event.getRoutingKey(), payload);
            log.info("Published event to broker topic {} with routing key {}", event.getTopic(), event.getRoutingKey());
        } catch (JsonProcessingException e) {
            log.error("Failed to serialize event payload for topic {}", event.getTopic(), e);
            throw new IllegalArgumentException("Invalid event payload", e);
        }
    }
}
