package com.chronicle.communications.microservice.delivery.email;

import com.chronicle.communications.common.CommunicationDeliveryException;
import com.chronicle.communications.common.model.Communication;
import com.chronicle.communications.microservice.delivery.common.AbstractDeliveryMicroService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class EmailDeliveryMicroService extends AbstractDeliveryMicroService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailDeliveryMicroService.class);

    public EmailDeliveryMicroService(ObjectMapper objectMapper) {
        super(objectMapper);
    }

    @JmsListener(destination = "${communications.delivery.email}")
    public void process(String message) {
        doProcess(message);
    }

    @Override
    protected void validate(Communication communication) {
        // TODO: Implement
    }

    @Override
    protected void send(Communication communication) {
        LOGGER.debug("Sending email from: {} to {}, for Communication: {}",
                communication.sender().orElse(null),
                communication.communicationRequest().recipients().orElseThrow(
                        () -> new CommunicationDeliveryException("Recipients cannot be empty.")),
                communication);
        // TODO: Implement
    }

    @Override
    protected Logger getLogger() {
        return LOGGER;
    }
}
