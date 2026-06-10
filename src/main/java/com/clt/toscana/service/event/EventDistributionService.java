package com.clt.toscana.service.event;

import com.clt.toscana.model.dto.EventRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

/**
 * Service for distributing events internally and externally.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class EventDistributionService {

    private final ApplicationEventPublisher eventPublisher;

    /**
     * Publishes an event internally via Spring ApplicationEvent
     * and delegates to the active message broker.
     */
    public void publish(EventRequest request) {
        log.info("Publishing event to topic: {}, routingKey: {}", request.getTopic(), request.getRoutingKey());
        CommEvent event = new CommEvent(this, request.getTopic(), request.getRoutingKey(), request.getPayload());
        eventPublisher.publishEvent(event);
        log.info("Event published to internal bus");
    }
}
