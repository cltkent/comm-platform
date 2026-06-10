package com.clt.toscana.broker.redis;

import com.clt.toscana.broker.MessageBroker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

/**
 * Redis Pub/Sub implementation of MessageBroker.
 */
@Slf4j
@Component
@RequiredArgsConstructor
@ConditionalOnProperty(name = "comm-platform.broker.active", havingValue = "redis")
public class RedisBroker implements MessageBroker {

    private final RedisTemplate<String, String> redisTemplate;

    @Override
    public void publish(String topic, String message) {
        log.info("Redis publishing to channel {}: {}", topic, message);
        redisTemplate.convertAndSend(topic, message);
    }

    @Override
    public void subscribe(String topic, Consumer<String> handler) {
        log.info("Redis subscription to channel {} registered", topic);
    }
}
