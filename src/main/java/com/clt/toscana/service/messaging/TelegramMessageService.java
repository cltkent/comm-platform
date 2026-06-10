package com.clt.toscana.service.messaging;

import com.clt.toscana.model.dto.MessageRequest;
import com.clt.toscana.model.enums.MessageChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

/**
 * Telegram messaging service implementation.
 * Skeleton implementation - integrate with telegrambots library for full functionality.
 */
@Slf4j
@Service
@ConditionalOnProperty(name = "comm-platform.messaging.telegram.enabled", havingValue = "true", matchIfMissing = true)
public class TelegramMessageService implements MessageChannelService {

    @Override
    public MessageChannel getChannel() {
        return MessageChannel.TELEGRAM;
    }

    @Override
    public void sendMessage(MessageRequest request) {
        log.info("Sending Telegram message to: {}, content: {}", request.getRecipient(), request.getContent());
        log.info("Telegram message sent (skeleton)");
    }
}
