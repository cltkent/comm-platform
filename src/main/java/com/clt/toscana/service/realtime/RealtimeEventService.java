package com.clt.toscana.service.realtime;

import com.clt.toscana.history.RealtimeEventRepository;
import com.clt.toscana.model.entity.RealtimeEvent;
import com.clt.toscana.model.enums.EventStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * Service for broadcasting realtime events via WebSocket and SSE.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class RealtimeEventService {

    private final SimpMessagingTemplate messagingTemplate;
    private final SseEmitterRegistry sseEmitterRegistry;
    private final RealtimeEventRepository eventRepository;

    /**
     * Broadcasts an event to all WebSocket subscribers on /topic/events.
     */
    public void broadcastToTopic(String payload) {
        log.info("Broadcasting to /topic/events: {}", payload);
        messagingTemplate.convertAndSend("/topic/events", payload);
        saveEvent("WEBSOCKET", payload, null, EventStatus.DELIVERED);
    }

    /**
     * Sends an event to a specific user queue.
     */
    public void sendToUser(String userId, String payload) {
        log.info("Sending to user {}: {}", userId, payload);
        messagingTemplate.convertAndSendToUser(userId, "/queue/events", payload);
        saveEvent("WEBSOCKET_USER", payload, userId, EventStatus.DELIVERED);
    }

    /**
     * Broadcasts an event to all SSE subscribers.
     */
    public void broadcastSse(String payload) {
        Map<String, SseEmitter> emitters = sseEmitterRegistry.getAll();
        emitters.forEach((clientId, emitter) -> {
            try {
                emitter.send(SseEmitter.event().name("event").data(payload));
                saveEvent("SSE", payload, clientId, EventStatus.DELIVERED);
            } catch (IOException e) {
                log.warn("Failed to send SSE to client {}: {}", clientId, e.getMessage());
                saveEvent("SSE", payload, clientId, EventStatus.FAILED);
            }
        });
    }

    private void saveEvent(String channel, String payload, String clientId, EventStatus status) {
        RealtimeEvent event = RealtimeEvent.builder()
                .channel(channel)
                .payload(payload)
                .clientId(clientId)
                .timestamp(LocalDateTime.now())
                .status(status)
                .build();
        eventRepository.save(event);
    }

    public Page<RealtimeEvent> getHistory(String clientId, Pageable pageable) {
        if (clientId != null && !clientId.isBlank()) {
            return eventRepository.findByClientIdOrderByTimestampDesc(clientId, pageable);
        }
        return eventRepository.findAllByOrderByTimestampDesc(pageable);
    }
}
