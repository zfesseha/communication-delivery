package com.chronicle.communications.common.service.queue.activemq;

import com.chronicle.communications.common.model.Communication;
import com.chronicle.communications.common.service.queue.QueueingService;
import com.chronicle.communications.common.service.queue.destination.DestinationResolver;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class RequestQueueingService extends ActiveMQQueueingService {

    public RequestQueueingService(JmsTemplate jmsTemplate,
                                  @Qualifier("singleDestinationResolver") DestinationResolver destinationResolver,
                                  ObjectMapper objectMapper) {
        super(jmsTemplate, destinationResolver, objectMapper);
    }
}
