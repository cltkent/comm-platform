package com.clt.toscana.service.messaging;

import com.clt.toscana.model.dto.MessageRequest;
import com.clt.toscana.model.enums.MessageChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Dispatches messages to the appropriate channel service.
 */
@Slf4j
@Service
public class MessageDispatcher {

    private final Map<MessageChannel, MessageChannelService> channelServices;

    public MessageDispatcher(List<MessageChannelService> services) {
        this.channelServices = services.stream()
                .collect(Collectors.toMap(MessageChannelService::getChannel, Function.identity()));
    }

    /**
     * Dispatches a message request to the appropriate channel.
     */
    public void dispatch(MessageRequest request) throws Exception {
        MessageChannelService service = channelServices.get(request.getChannel());
        if (service == null) {
            throw new IllegalArgumentException("Unsupported channel: " + request.getChannel());
        }
        log.info("Dispatching {} message to {}", request.getChannel(), request.getRecipient());
        service.sendMessage(request);
    }
}
