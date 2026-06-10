package com.clt.toscana.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Broker configuration properties.
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "comm-platform.broker")
public class BrokerProperties {
    private String active = "kafka";
}
