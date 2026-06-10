package com.clt.toscana.broker.kafka;

import com.clt.toscana.broker.MessageBroker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

/**
 * Kafka implementation of MessageBroker.
 */
@Slf4j
@Component
@RequiredArgsConstructor
@ConditionalOnProperty(name = "comm-platform.broker.active", havingValue = "kafka")
public class KafkaBroker implements MessageBroker {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void publish(String topic, String message) {
        log.info("Kafka publishing to topic {}: {}", topic, message);
        kafkaTemplate.send(topic, message);
    }

    @Override
    public void subscribe(String topic, Consumer<String> handler) {
        log.info("Kafka subscription to topic {} registered", topic);
    }
}
