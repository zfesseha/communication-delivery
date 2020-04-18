package com.chronicle.communications.common.service.queue.destination;

import com.chronicle.communications.common.model.Communication;

public interface DestinationResolver {

    String destination(Communication communication);
}
