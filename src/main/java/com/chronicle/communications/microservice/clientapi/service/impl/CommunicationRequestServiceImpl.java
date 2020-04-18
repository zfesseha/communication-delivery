package com.chronicle.communications.microservice.clientapi.service.impl;

import com.chronicle.communications.common.model.CommunicationRequest;
import com.chronicle.communications.common.model.ImmutableCommunicationRequest;
import com.chronicle.communications.microservice.clientapi.service.CommunicationRequestService;
import com.chronicle.communications.microservice.queueing.QueuingServiceClient;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CommunicationRequestServiceImpl implements CommunicationRequestService {

    private QueuingServiceClient queuingServiceClient;

    public CommunicationRequestServiceImpl(QueuingServiceClient queuingServiceClient) {
        this.queuingServiceClient = queuingServiceClient;
    }

    @Override
    public CommunicationDTO process(CommunicationRequest communicationRequest) {
        CommunicationDTO communicationDTO = ImmutableCommunicationDTO.builder()
                .communicationRequest(communicationRequest)
                .communication(
                        ImmutableCommunication.builder()
                                .id(UUID.randomUUID())
                                .type(communicationRequest.type())
                                .clientId(communicationRequest.clientId())
                                .batchId(communicationRequest.batchId())
                                .build()
                )
                .build();
        String result = queuingServiceClient.queue(communicationDTO.communication());
        return communicationDTO;
    }
}
