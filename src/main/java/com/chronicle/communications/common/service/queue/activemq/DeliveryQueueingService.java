package com.chronicle.communications.common.service.queue.activemq;

import com.chronicle.communications.common.service.queue.destination.DestinationResolver;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class DeliveryQueueingService extends ActiveMQQueueingService {

    public DeliveryQueueingService(JmsTemplate jmsTemplate,
                                   @Qualifier("communicationTypeBasedDestinationResolver") DestinationResolver destinationResolver,
                                   ObjectMapper objectMapper) {
        super(jmsTemplate, destinationResolver, objectMapper);
    }
}
