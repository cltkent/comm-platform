package com.clt.toscana.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;

/**
 * Firebase configuration for push notifications via FCM.
 */
@Slf4j
@Configuration
public class FirebaseConfig {

    @Value("${comm-platform.firebase.service-account-file:firebase-service-account.json}")
    private String serviceAccountFile;

    @PostConstruct
    public void initialize() {
        try {
            if (FirebaseApp.getApps().isEmpty()) {
                InputStream serviceAccount = getClass().getClassLoader().getResourceAsStream(serviceAccountFile);
                if (serviceAccount == null) {
                    log.warn("Firebase service account file not found: {}. FCM will be disabled.", serviceAccountFile);
                    return;
                }
                try (serviceAccount) {
                    FirebaseOptions options = FirebaseOptions.builder()
                            .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                            .build();
                    FirebaseApp.initializeApp(options);
                }
                log.info("Firebase initialized successfully");
            }
        } catch (IOException e) {
            log.warn("Failed to initialize Firebase: {}. FCM will be disabled.", e.getMessage());
        }
    }
}
