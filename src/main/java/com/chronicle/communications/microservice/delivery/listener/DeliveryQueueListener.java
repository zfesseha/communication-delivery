package com.chronicle.communications.microservice.delivery.listener;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class DeliveryQueueListener {

    @JmsListener(destination = "${communications.delivery.email}")
    public void process(String message) {
        System.out.println("message");
    }
}
