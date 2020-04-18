package com.chronicle.communications.common.service.queue.impl;

import com.chronicle.communications.common.model.Communication;
import com.chronicle.communications.common.service.queue.DestinationResolver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SingleDestinationResolver implements DestinationResolver {
    @Value("${communications.queueing.destination.email}")
    private String destination;

    @Override
    public String destination(Communication communication) {
        return destination;
    }
}
