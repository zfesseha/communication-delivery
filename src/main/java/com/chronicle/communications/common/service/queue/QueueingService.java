package com.chronicle.communications.common.service.queue;

import com.chronicle.communications.common.model.Communication;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface QueueingService {

    // TODO: Remove Exception from signature
    String sendToQueue(Communication communication) throws JsonProcessingException;
}
