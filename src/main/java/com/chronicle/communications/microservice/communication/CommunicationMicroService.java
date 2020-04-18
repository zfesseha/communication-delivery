package com.chronicle.communications.microservice.communication;

import com.chronicle.communications.common.model.Communication;
import com.chronicle.communications.common.model.CommunicationRequest;
import com.chronicle.communications.microservice.communication.service.CommunicationService;
import org.springframework.stereotype.Component;

@Component
public class CommunicationMicroService {

    private CommunicationService communicationService;

    public CommunicationMicroService(CommunicationService communicationService) {
        this.communicationService = communicationService;
    }

    public Communication create(CommunicationRequest communicationRequest) {
        return communicationService.create(communicationRequest);
    }
}
