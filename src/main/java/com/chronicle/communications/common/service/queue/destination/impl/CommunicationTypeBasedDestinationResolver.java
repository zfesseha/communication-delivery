package com.chronicle.communications.common.service.queue.destination.impl;

import com.chronicle.communications.common.CommunicationDeliveryException;
import com.chronicle.communications.common.model.Communication;
import com.chronicle.communications.common.service.queue.destination.DestinationResolver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CommunicationTypeBasedDestinationResolver implements DestinationResolver {

    @Value("${communications.queueing.destination.email}")
    private String emailDestination;
    @Value("${communications.queueing.destination.sms}")
    private String smsDestination;
    @Value("${communications.queueing.destination.push}")
    private String pushNotificationDestination;
    @Value("${communications.queueing.destination.iam}")
    private String inAppMessageDestination;

    @Override
    public String destination(Communication communication) {
        switch (communication.communicationRequest().type()) {
            case EMAIL:
                return emailDestination;
            case SMS:
                return smsDestination;
            case PUSH_NOTIFICATION:
                return pushNotificationDestination;
            case IN_APP_MESSAGE:
                return inAppMessageDestination;
            default:
                throw new CommunicationDeliveryException("Unknown CommunicationType");
        }
    }
}
