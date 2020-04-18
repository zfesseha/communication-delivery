package com.chronicle.communications.microservice.scheduling;

import com.chronicle.communications.common.CommunicationDeliveryException;
import com.chronicle.communications.common.model.Communication;
import com.chronicle.communications.common.model.CommunicationRequest;
import com.chronicle.communications.common.model.ImmutableCommunication;
import com.chronicle.communications.common.service.queue.QueueingService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class SchedulingServiceClient {

    private QueueingService queueingService;

    private static final Logger LOGGER = LoggerFactory.getLogger(SchedulingServiceClient.class);

    public SchedulingServiceClient(@Qualifier("requestQueueingService") QueueingService queueingService) {
        this.queueingService = queueingService;
    }

    // TODO: This should be a proper response object.
    public String queue(CommunicationRequest communicationRequest) {
        UUID communicationId = communicationRequest.communicationId()
                .orElseThrow(() -> new CommunicationDeliveryException("CommunicationRequest doesn't have ID."));
        // TODO: Use MessageService to render Communication
        Communication communication = ImmutableCommunication.builder()
                .id(communicationId)
                .communicationRequest(communicationRequest)
                .build();
        try {
            return this.queueingService.sendToQueue(communication);
        } catch (JsonProcessingException e) {
            LOGGER.error("There was a problem sending message {}.", communication, e);
            throw new CommunicationDeliveryException("There was a problem sending message: " + communication);
        }
    }
}
