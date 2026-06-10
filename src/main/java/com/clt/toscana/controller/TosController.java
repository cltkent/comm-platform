package com.clt.toscana.controller;

import com.clt.toscana.model.dto.ApiResponse;
import com.clt.toscana.model.dto.TosInboundEvent;
import com.clt.toscana.tos.TosInboundService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for TOS system integration.
 */
@RestController
@RequestMapping("/api/v1/tos")
@RequiredArgsConstructor
@Tag(name = "TOS", description = "TOS system integration (inbound/outbound)")
public class TosController {

    private final TosInboundService tosInboundService;

    @PostMapping("/inbound")
    @Operation(summary = "Receive an inbound event from TOS")
    public ResponseEntity<ApiResponse<Void>> receiveInbound(@RequestBody TosInboundEvent event) {
        tosInboundService.processInboundEvent(event);
        return ResponseEntity.ok(ApiResponse.success("Event received", null));
    }
}
