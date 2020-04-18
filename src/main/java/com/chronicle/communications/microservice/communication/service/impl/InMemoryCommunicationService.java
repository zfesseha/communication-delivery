package com.chronicle.communications.microservice.communication.service.impl;

import com.chronicle.communications.common.exception.NotFoundException;
import com.chronicle.communications.common.model.*;
import com.chronicle.communications.common.model.enums.CommunicationEvent;
import com.chronicle.communications.microservice.communication.service.CommunicationService;
import com.google.common.collect.ImmutableList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.*;

import static com.chronicle.communications.common.model.enums.CommunicationEvent.CREATED;

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
        communication = withLogEvent(communication, CREATED, null);
        storage.put(id, communication);
        LOGGER.info("Created new Communication with ID: {}, Communication: {}", id, communication);
        return communication;
    }

    @Override
    public Communication logEvent(UUID communicationId, CommunicationEvent event) {
        return logEvent(communicationId, event, null);
    }

    @Override
    public Communication logEvent(UUID communicationId, CommunicationEvent event, Map<String, String> metadata) {
        Communication communication = withLogEvent(getCommunication(communicationId), event, metadata);
        storage.put(communicationId, communication);
        LOGGER.info("Successfully saved log event for communication with ID: {}, {}", communicationId, communication);
        return communication;
    }

    @Override
    public List<Communication> getAll() {
        return ImmutableList.copyOf(storage.values());
    }

    @Override
    public Optional<Communication> get(UUID communicationId) {
        return Optional.ofNullable(getCommunication(communicationId));
    }

    private Communication withLogEvent(Communication communication, CommunicationEvent event, Map<String, String> metadata) {
        ImmutableCommunicationLog communicationLog = ImmutableCommunicationLog.builder()
                .id(UUID.randomUUID())
                .communicationId(communication.id())
                .event(event)
                .metadata(Optional.ofNullable(metadata))
                .createdAt(OffsetDateTime.now())
                .build();
        return ImmutableCommunication.builder()
                .from(communication)
                .logs(ImmutableList.<CommunicationLog>builder()
                        .addAll(communication.logs().orElse(ImmutableList.of()))
                        .add(communicationLog)
                        .build())
                // TODO: Add back
//                .addLogs(communicationLog
//                )
                .build();
    }

    private Communication getCommunication(UUID communicationId) {
        Communication communication = storage.get(communicationId);
        if (communication == null) {
            throw new NotFoundException(communicationId, "Communication");
        }
        LOGGER.debug("Successfully retrieved communication for ID: {}, {}", communicationId, communication);
        return communication;
    }
}
