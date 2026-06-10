package com.clt.toscana.service.messaging;

import com.clt.toscana.model.dto.MessageRequest;
import com.clt.toscana.model.enums.MessageChannel;

/**
 * Abstraction interface for message channels.
 */
public interface MessageChannelService {
    /**
     * Returns the channel this service handles.
     */
    MessageChannel getChannel();

    /**
     * Sends a message via this channel.
     */
    void sendMessage(MessageRequest request) throws Exception;
}
