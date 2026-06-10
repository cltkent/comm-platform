package com.clt.toscana.broker;

import java.util.function.Consumer;

/**
 * Abstraction interface for message brokers.
 */
public interface MessageBroker {
    /**
     * Publishes a message to the given topic.
     */
    void publish(String topic, String message);

    /**
     * Publishes a message using a broker-specific routing key when supported.
     */
    default void publish(String topic, String routingKey, String message) {
        publish(topic, message);
    }

    /**
     * Subscribes to a topic and registers a message handler.
     */
    void subscribe(String topic, Consumer<String> handler);
}
