package com.chronicle.communications.microservice.communication.service;

import com.chronicle.communications.common.model.Communication;
import com.chronicle.communications.common.model.CommunicationRequest;

public interface CommunicationService {

    Communication create(CommunicationRequest communicationRequest);
}
