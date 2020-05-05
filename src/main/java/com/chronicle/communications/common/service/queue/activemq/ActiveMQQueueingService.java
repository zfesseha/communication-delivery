package com.chronicle.communications.common.service.queue.activemq;

import com.chronicle.communications.common.model.Communication;
import com.chronicle.communications.common.service.queue.QueueingService;
import com.chronicle.communications.common.service.queue.destination.DestinationResolver;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jms.core.JmsTemplate;

public class ActiveMQQueueingService implements QueueingService {

    private JmsTemplate jmsTemplate;
    private DestinationResolver destinationResolver;
    private ObjectMapper objectMapper;

    public ActiveMQQueueingService(JmsTemplate jmsTemplate,
                                   DestinationResolver destinationResolver,
                                   ObjectMapper objectMapper) {
        this.jmsTemplate = jmsTemplate;
        this.destinationResolver = destinationResolver;
        this.objectMapper = objectMapper;
    }

    @Override
    public String sendToQueue(Communication communication) throws JsonProcessingException {
        String destination = destinationResolver.destination(communication);
        String message = objectMapper.writeValueAsString(communication);
        jmsTemplate.convertAndSend(destination, message);
        return message;
    }
}
