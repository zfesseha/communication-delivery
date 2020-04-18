package com.chronicle.communications.microservice.delivery.common;

import com.chronicle.communications.common.model.Communication;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;

public abstract class AbstractDeliveryMicroService {

    private ObjectMapper objectMapper;

    public AbstractDeliveryMicroService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    protected void doProcess(String message) {
        getLogger().debug("Successfully received message from delivery queue: {}", message);
        try {
            Communication communication = objectMapper.readValue(message, Communication.class);
            getLogger().debug("Validating communication: {}", communication);
            validate(communication);
            getLogger().debug("Attempting to deliver communication: {}", communication);
            send(communication);
            // TODO: Record result of Delivery Comm Service
            // TODO: Requeue if necessary.
        } catch (JsonProcessingException e) {
            getLogger().error("Error processing message from delivery queue: {}", message);
        }
    }

    protected abstract void validate(Communication communication);

    protected abstract void send(Communication communication);

    protected abstract Logger getLogger();
}
