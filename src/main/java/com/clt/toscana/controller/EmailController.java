package com.clt.toscana.controller;

import com.clt.toscana.model.dto.ApiResponse;
import com.clt.toscana.model.dto.EmailRequest;
import com.clt.toscana.service.email.EmailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for email operations.
 */
@RestController
@RequestMapping("/api/v1/email")
@RequiredArgsConstructor
@Tag(name = "Email", description = "Email sending operations")
public class EmailController {

    private final EmailService emailService;

    @PostMapping("/send")
    @Operation(summary = "Send an email")
    public ResponseEntity<ApiResponse<Void>> sendEmail(@RequestBody EmailRequest request) throws Exception {
        emailService.sendEmail(request);
        return ResponseEntity.ok(ApiResponse.success("Email sent successfully", null));
    }
}
