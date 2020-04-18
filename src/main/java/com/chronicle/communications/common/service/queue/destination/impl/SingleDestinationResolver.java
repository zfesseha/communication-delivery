package com.chronicle.communications.common.service.queue.destination.impl;

import com.chronicle.communications.common.model.Communication;
import com.chronicle.communications.common.service.queue.destination.DestinationResolver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SingleDestinationResolver implements DestinationResolver {
    @Value("${communications.client-api.request-queue}")
    private String destination;

    @Override
    public String destination(Communication communication) {
        return destination;
    }
}
