package com.clt.toscana.admin;

import com.clt.toscana.model.dto.ApiResponse;
import com.clt.toscana.model.entity.RealtimeEvent;
import com.clt.toscana.service.realtime.RealtimeEventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Admin REST API for monitoring and history.
 */
@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
@Tag(name = "Admin", description = "Admin operations and history")
public class AdminController {

    private final RealtimeEventService realtimeEventService;

    @GetMapping("/history")
    @Operation(summary = "Get realtime event history")
    public ResponseEntity<ApiResponse<Page<RealtimeEvent>>> getHistory(
            @RequestParam(required = false) String clientId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50") int size) {
        Page<RealtimeEvent> history = realtimeEventService.getHistory(clientId, PageRequest.of(page, size));
        return ResponseEntity.ok(ApiResponse.success(history));
    }

    @PostMapping("/broadcast")
    @Operation(summary = "Broadcast a message to all connected WebSocket clients")
    public ResponseEntity<ApiResponse<Void>> broadcast(@RequestBody String message) {
        realtimeEventService.broadcastToTopic(message);
        return ResponseEntity.ok(ApiResponse.success("Broadcast sent", null));
    }

    @PostMapping("/sse/broadcast")
    @Operation(summary = "Broadcast a message to all SSE subscribers")
    public ResponseEntity<ApiResponse<Void>> sseBroadcast(@RequestBody String message) {
        realtimeEventService.broadcastSse(message);
        return ResponseEntity.ok(ApiResponse.success("SSE broadcast sent", null));
    }
}
