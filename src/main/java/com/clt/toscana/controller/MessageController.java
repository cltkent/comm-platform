package com.clt.toscana.controller;

import com.clt.toscana.model.dto.ApiResponse;
import com.clt.toscana.model.dto.MessageRequest;
import com.clt.toscana.service.messaging.MessageDispatcher;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for sending messages via various channels.
 */
@RestController
@RequestMapping("/api/v1/message")
@RequiredArgsConstructor
@Tag(name = "Message", description = "Message sending via LINE, Telegram, etc.")
public class MessageController {

    private final MessageDispatcher messageDispatcher;

    @PostMapping("/send")
    @Operation(summary = "Send a message via specified channel")
    public ResponseEntity<ApiResponse<Void>> sendMessage(@RequestBody MessageRequest request) throws Exception {
        messageDispatcher.dispatch(request);
        return ResponseEntity.ok(ApiResponse.success("Message sent successfully", null));
    }
}
