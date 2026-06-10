package com.clt.toscana.broker.rabbitmq;

import com.clt.toscana.broker.MessageBroker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

/**
 * RabbitMQ implementation of MessageBroker.
 */
@Slf4j
@Component
@RequiredArgsConstructor
@ConditionalOnProperty(name = "comm-platform.broker.active", havingValue = "rabbitmq")
public class RabbitMQBroker implements MessageBroker {

    private final RabbitTemplate rabbitTemplate;

    @Override
    public void publish(String topic, String message) {
        log.info("RabbitMQ publishing to topic {}: {}", topic, message);
        rabbitTemplate.convertAndSend(topic, message);
    }

    @Override
    public void publish(String topic, String routingKey, String message) {
        log.info("RabbitMQ publishing to exchange {} with routing key {}", topic, routingKey);
        rabbitTemplate.convertAndSend(topic, routingKey, message);
    }

    @Override
    public void subscribe(String topic, Consumer<String> handler) {
        log.info("RabbitMQ subscription to topic {} registered", topic);
    }
}
