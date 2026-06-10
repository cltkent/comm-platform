package com.clt.toscana.controller;

import com.clt.toscana.model.dto.ApiResponse;
import com.clt.toscana.model.dto.EventRequest;
import com.clt.toscana.service.event.EventDistributionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for event distribution.
 */
@RestController
@RequestMapping("/api/v1/events")
@RequiredArgsConstructor
@Tag(name = "Events", description = "Event distribution and publishing")
public class EventController {

    private final EventDistributionService eventDistributionService;

    @PostMapping("/publish")
    @Operation(summary = "Publish an event")
    public ResponseEntity<ApiResponse<Void>> publishEvent(@RequestBody EventRequest request) {
        eventDistributionService.publish(request);
        return ResponseEntity.ok(ApiResponse.success("Event published successfully", null));
    }
}
