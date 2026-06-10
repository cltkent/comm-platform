package com.clt.toscana.service.notification;

import com.clt.toscana.model.dto.NotificationRequest;
import com.google.firebase.FirebaseApp;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Service for sending push notifications via Firebase Cloud Messaging (FCM).
 */
@Slf4j
@Service
public class PushNotificationService {

    /**
     * Sends a push notification to a single device or a topic.
     */
    public void sendNotification(NotificationRequest request) throws FirebaseMessagingException {
        if (FirebaseApp.getApps().isEmpty()) {
            log.warn("Firebase not initialized. Skipping push notification.");
            return;
        }

        Notification notification = Notification.builder()
                .setTitle(request.getTitle())
                .setBody(request.getBody())
                .build();

        Message.Builder messageBuilder = Message.builder().setNotification(notification);

        if (request.getData() != null) {
            messageBuilder.putAllData(request.getData());
        }

        if (request.getTopic() != null && !request.getTopic().isBlank()) {
            messageBuilder.setTopic(request.getTopic());
            log.info("Sending FCM notification to topic: {}", request.getTopic());
        } else if (request.getToken() != null && !request.getToken().isBlank()) {
            messageBuilder.setToken(request.getToken());
            log.info("Sending FCM notification to token: {}", request.getToken());
        } else {
            throw new IllegalArgumentException("Either token or topic must be provided");
        }

        String response = FirebaseMessaging.getInstance().send(messageBuilder.build());
        log.info("FCM notification sent, messageId: {}", response);
    }
}
