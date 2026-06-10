package com.clt.toscana.tos;

import com.clt.toscana.config.TosConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * Client for sending outbound requests to the TOS system.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class TosClient {

    private final WebClient.Builder webClientBuilder;
    private final TosConfig tosConfig;

    /**
     * Sends an event/payload to the TOS system.
     */
    public Mono<String> sendToTos(String endpoint, Map<String, Object> payload) {
        log.info("Sending to TOS system: {}{}", tosConfig.getBaseUrl(), endpoint);
        return webClientBuilder.baseUrl(tosConfig.getBaseUrl())
                .build()
                .post()
                .uri(endpoint)
                .header("X-API-Key", tosConfig.getApiKey())
                .bodyValue(payload)
                .retrieve()
                .bodyToMono(String.class)
                .doOnSuccess(resp -> log.info("TOS response: {}", resp))
                .doOnError(err -> log.error("TOS error: {}", err.getMessage()));
    }
}
