package com.chronicle.communications.microservice.communication;

import com.chronicle.communications.common.model.Communication;
import com.chronicle.communications.common.model.CommunicationRequest;
import com.chronicle.communications.common.model.enums.CommunicationEvent;
import com.chronicle.communications.microservice.communication.service.CommunicationService;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;

@Component
public class CommunicationMicroService {

    private CommunicationService communicationService;

    public CommunicationMicroService(CommunicationService communicationService) {
        this.communicationService = communicationService;
    }

    public Communication create(CommunicationRequest communicationRequest) {
        return communicationService.create(communicationRequest);
    }

    public Communication logEvent(UUID communicationId, CommunicationEvent event) {
        return communicationService.logEvent(communicationId, event);
    }

    public Communication logEvent(UUID communicationId, CommunicationEvent event, Map<String, String> metadata) {
        return communicationService.logEvent(communicationId, event, metadata);
    }
}
