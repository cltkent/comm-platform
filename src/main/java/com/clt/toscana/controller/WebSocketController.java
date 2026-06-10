package com.clt.toscana.controller;

import com.clt.toscana.service.realtime.RealtimeEventService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

/**
 * WebSocket/STOMP message controller.
 */
@Controller
@RequiredArgsConstructor
@Tag(name = "WebSocket", description = "WebSocket STOMP endpoints")
public class WebSocketController {

    private final RealtimeEventService realtimeEventService;

    @MessageMapping("/broadcast")
    @SendTo("/topic/events")
    public String broadcast(String message) {
        realtimeEventService.broadcastToTopic(message);
        return message;
    }
}
