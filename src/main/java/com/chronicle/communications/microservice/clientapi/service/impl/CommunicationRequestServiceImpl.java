package com.chronicle.communications.microservice.clientapi.service.impl;

import com.chronicle.communications.common.CommunicationDeliveryException;
import com.chronicle.communications.common.model.Communication;
import com.chronicle.communications.common.model.CommunicationRequest;
import com.chronicle.communications.common.model.ImmutableCommunicationRequest;
import com.chronicle.communications.common.service.queue.QueueingService;
import com.chronicle.communications.microservice.clientapi.service.CommunicationRequestService;
import com.chronicle.communications.microservice.communication.CommunicationMicroService;
import com.chronicle.communications.microservice.scheduling.SchedulingMicroService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;

import static com.chronicle.communications.common.model.enums.CommunicationEvent.QUEUED_FOR_PROCESSING;

@Component
public class CommunicationRequestServiceImpl implements CommunicationRequestService {

    private CommunicationMicroService communicationMicroService;
    private QueueingService queueingService;

    private static final Logger LOGGER = LoggerFactory.getLogger(SchedulingMicroService.class);

    public CommunicationRequestServiceImpl(CommunicationMicroService communicationMicroService,
                                           @Qualifier("requestQueueingService") QueueingService queueingService) {
        this.communicationMicroService = communicationMicroService;
        this.queueingService = queueingService;
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
