package com.clt.toscana.model.dto;

import com.clt.toscana.model.enums.MessageChannel;
import lombok.Data;

/**
 * Request DTO for sending a message via a specific channel.
 */
@Data
public class MessageRequest {
    private MessageChannel channel;
    private String recipient;
    private String content;
}
