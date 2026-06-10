package com.clt.toscana.controller;

import com.clt.toscana.model.dto.ApiResponse;
import com.clt.toscana.model.dto.NotificationRequest;
import com.clt.toscana.service.notification.PushNotificationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for push notification operations.
 */
@RestController
@RequestMapping("/api/v1/notification")
@RequiredArgsConstructor
@Tag(name = "Notification", description = "Push notification via FCM")
public class NotificationController {

    private final PushNotificationService pushNotificationService;

    @PostMapping("/push")
    @Operation(summary = "Send a push notification")
    public ResponseEntity<ApiResponse<Void>> sendNotification(@RequestBody NotificationRequest request) throws Exception {
        pushNotificationService.sendNotification(request);
        return ResponseEntity.ok(ApiResponse.success("Notification sent successfully", null));
    }
}
