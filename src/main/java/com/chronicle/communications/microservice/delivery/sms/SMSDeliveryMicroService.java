package com.chronicle.communications.microservice.delivery.sms;

import com.chronicle.communications.common.exception.CommunicationDeliveryException;
import com.chronicle.communications.common.model.Communication;
import com.chronicle.communications.microservice.delivery.common.AbstractDeliveryMicroService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class SMSDeliveryMicroService extends AbstractDeliveryMicroService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SMSDeliveryMicroService.class);

    public SMSDeliveryMicroService(ObjectMapper objectMapper) {
        super(objectMapper);
    }

    @JmsListener(destination = "${communications.delivery.sms}")
    public void process(String message) {
        doProcess(message);
    }

    @Override
    protected void validate(Communication communication) {
        // TODO: Implement
    }

    @Override
    protected void send(Communication communication) {
        LOGGER.debug("Sending sms from: {} to {}, for Communication: {}",
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
