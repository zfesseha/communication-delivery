package com.chronicle.communications.microservice.queueing.service;

import com.chronicle.communications.common.model.Communication;

public interface DestinationResolver {

    String destination(Communication communication);
}
