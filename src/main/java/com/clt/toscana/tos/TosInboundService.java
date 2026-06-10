package com.clt.toscana.tos;

import com.clt.toscana.model.dto.TosInboundEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Service for handling inbound events from the TOS system.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class TosInboundService {

    /**
     * Processes an inbound event from TOS.
     */
    public void processInboundEvent(TosInboundEvent event) {
        log.info("Received inbound event from TOS: type={}, source={}", event.getEventType(), event.getSource());
    }
}
