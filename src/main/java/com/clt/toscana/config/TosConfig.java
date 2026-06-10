package com.clt.toscana.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * TOS system configuration properties.
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "comm-platform.tos")
public class TosConfig {
    private String baseUrl = "http://tos-system:8080";
    private String apiKey = "your-api-key";
}
