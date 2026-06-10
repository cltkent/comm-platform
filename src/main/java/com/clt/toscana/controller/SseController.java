package com.clt.toscana.controller;

import com.clt.toscana.service.realtime.SseEmitterRegistry;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

/**
 * REST controller for Server-Sent Events (SSE) subscriptions.
 */
@RestController
@RequestMapping("/api/v1/sse")
@RequiredArgsConstructor
@Tag(name = "SSE", description = "Server-Sent Events subscription")
public class SseController {

    private final SseEmitterRegistry sseEmitterRegistry;

    @GetMapping("/subscribe/{clientId}")
    @Operation(summary = "Subscribe to SSE events")
    public SseEmitter subscribe(@PathVariable String clientId) {
        return sseEmitterRegistry.create(clientId);
    }
}
