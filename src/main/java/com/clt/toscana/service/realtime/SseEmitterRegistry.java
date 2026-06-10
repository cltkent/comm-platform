package com.clt.toscana.service.realtime;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Registry for managing active SSE emitters per client.
 */
@Slf4j
@Component
public class SseEmitterRegistry {

    private static final long SSE_TIMEOUT_MS = 30 * 60 * 1000L;

    private final Map<String, SseEmitter> emitters = new ConcurrentHashMap<>();

    public SseEmitter create(String clientId) {
        SseEmitter emitter = new SseEmitter(SSE_TIMEOUT_MS);
        emitters.put(clientId, emitter);
        emitter.onCompletion(() -> emitters.remove(clientId));
        emitter.onTimeout(() -> emitters.remove(clientId));
        emitter.onError(error -> emitters.remove(clientId));
        log.info("SSE emitter registered for client: {}", clientId);
        return emitter;
    }

    public Map<String, SseEmitter> getAll() {
        return Collections.unmodifiableMap(emitters);
    }

    public SseEmitter get(String clientId) {
        return emitters.get(clientId);
    }
}
