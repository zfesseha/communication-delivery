package com.chronicle.communications.microservice.communication.service.impl;

import com.chronicle.communications.common.model.Communication;
import com.chronicle.communications.common.model.CommunicationRequest;
import com.chronicle.communications.common.model.ImmutableCommunication;
import com.chronicle.communications.common.model.ImmutableCommunicationRequest;
import com.chronicle.communications.microservice.communication.service.CommunicationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class InMemoryCommunicationService implements CommunicationService {

    private Map<UUID, Communication> storage;

    private static final Logger LOGGER = LoggerFactory.getLogger(InMemoryCommunicationService.class);

    public InMemoryCommunicationService() {
        storage = new HashMap<>();
    }

    @Override
    public Communication create(CommunicationRequest communicationRequest) {
        LOGGER.debug("Creating new Communication from request: {}", communicationRequest);
        UUID id = UUID.randomUUID();
        OffsetDateTime now = OffsetDateTime.now();
        Communication communication = ImmutableCommunication.builder()
                .id(id)
                .communicationRequest(ImmutableCommunicationRequest.builder()
                        .from(communicationRequest)
                        .communicationId(id)
                        .createdAt(now)
                        .build()
                )
                .createdAt(now)
                .build();
        storage.put(id, communication);
        LOGGER.info("Created new Communication with ID: {}, Communication: {}", id, communication);
        return communication;
    }
}
