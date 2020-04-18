package com.chronicle.communications.common.service.queue.activemq;

import com.chronicle.communications.common.model.Communication;
import com.chronicle.communications.common.service.queue.QueueingService;
import com.chronicle.communications.common.service.queue.destination.DestinationResolver;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

public class ActiveMQQueueingService implements QueueingService {

    private DestinationResolver destinationResolver;
    private ObjectMapper objectMapper;

    @Autowired
    public ActiveMQQueueingService(DestinationResolver destinationResolver,
                                   ObjectMapper objectMapper) {
        this.destinationResolver = destinationResolver;
        this.objectMapper = objectMapper;
    }

    @Override
    @SendTo("aasdf.asdsss")
    public String sendToQueue(Communication communication) throws JsonProcessingException {
        String destination = destinationResolver.destination(communication);
        String message = objectMapper.writeValueAsString(communication);
//        jmsTemplate.convertAndSend(destination, message);
        return message;
    }
}
