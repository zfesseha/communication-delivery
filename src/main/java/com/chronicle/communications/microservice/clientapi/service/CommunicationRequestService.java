package com.chronicle.communications.microservice.clientapi.service;

import com.chronicle.communications.common.model.CommunicationRequest;

public interface CommunicationRequestService {

    CommunicationRequest process(CommunicationRequest communicationRequest);

}
