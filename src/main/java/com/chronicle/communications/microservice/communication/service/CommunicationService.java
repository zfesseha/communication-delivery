package com.chronicle.communications.microservice.communication.service;

import com.chronicle.communications.common.model.Communication;
import com.chronicle.communications.common.model.CommunicationRequest;
import com.chronicle.communications.common.model.enums.CommunicationEvent;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public interface CommunicationService {

    Communication create(CommunicationRequest communicationRequest);

    Communication logEvent(UUID communicationId, CommunicationEvent event);

    Communication logEvent(UUID communicationId, CommunicationEvent event, Map<String, String> metadata);

    List<Communication> getAll();

    Optional<Communication> get(UUID communicationId);
}
