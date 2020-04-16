package com.chronicle.communications.microservice.clientapi.service;

import com.chronicle.communications.common.model.CommunicationDTO;
import com.chronicle.communications.common.model.CommunicationRequest;

public interface CommunicationRequestService {

    CommunicationDTO process(CommunicationRequest communicationRequest);

}
