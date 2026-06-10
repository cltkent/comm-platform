package com.clt.toscana.service.messaging;

import com.clt.toscana.model.dto.MessageRequest;
import com.clt.toscana.model.enums.MessageChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

/**
 * LINE messaging service implementation.
 * Skeleton implementation - integrate with line-bot-sdk-java for full functionality.
 */
@Slf4j
@Service
@ConditionalOnProperty(name = "comm-platform.messaging.line.enabled", havingValue = "true", matchIfMissing = true)
public class LineMessageService implements MessageChannelService {

    @Override
    public MessageChannel getChannel() {
        return MessageChannel.LINE;
    }

    @Override
    public void sendMessage(MessageRequest request) {
        log.info("Sending LINE message to recipient: {}, content: {}", request.getRecipient(), request.getContent());
        log.info("LINE message sent (skeleton)");
    }
}
