package com.chronicle.communications.microservice.queueing;

import com.chronicle.communications.common.model.Communication;
import com.chronicle.communications.microservice.queueing.service.QueueingService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class QueuingServiceClient {

    private QueueingService queueingService;

    private static final String ERROR_MESSAGE = "ERROR SENDING MESSAGE TO QUEUE";
    private static final Logger LOGGER = LoggerFactory.getLogger(QueuingServiceClient.class);

    public QueuingServiceClient(QueueingService queueingService) {
        this.queueingService = queueingService;
    }

    // TODO: This should be a proper response object.
    public String queue(Communication communication) {
        try {
            return this.queueingService.sendToQueue(communication);
        } catch (JsonProcessingException e) {
            LOGGER.error("There was a problem sending message {}.", communication, e);
            return ERROR_MESSAGE;
        }
    }
}
