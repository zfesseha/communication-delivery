package com.chronicle.communications.microservice.scheduling;

import com.chronicle.communications.common.exception.CommunicationDeliveryException;
import com.chronicle.communications.common.model.Communication;
import com.chronicle.communications.common.service.queue.QueueingService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class SchedulingMicroService {

    private QueueingService queueingService;
    private ObjectMapper objectMapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(SchedulingMicroService.class);

    public SchedulingMicroService(@Qualifier("deliveryQueueingService") QueueingService queueingService,
                                  ObjectMapper objectMapper) {
        this.queueingService = queueingService;
        this.objectMapper = objectMapper;
    }

    @JmsListener(destination = "${communications.queueing.source}")
    public void process(String communicationMessage) {
        LOGGER.debug("Read communication off the request queue: {}", communicationMessage);
        // TODO: Figure out what to do with message based on routing strategy
        // TODO: Use MessageService to render Communication
        try {
            Communication communication = objectMapper.readValue(communicationMessage, Communication.class);
            LOGGER.debug("Sending communication to delivery queue, {}", communication);
            this.queueingService.sendToQueue(communication);
            // TODO: Mark Comm in the Comm Service
            LOGGER.info("Successfully sent communication to delivery queue, {}", communication);
        } catch (JsonProcessingException e) {
            LOGGER.error("There was a problem processing message message {}.", communicationMessage, e);
            throw new CommunicationDeliveryException("There was a problem processing message message: " + communicationMessage);
        }
    }
}
