package com.chronicle.communications.common.exception;

// TODO: Handle occurrences of this Exception across application.
public class CommunicationDeliveryException extends RuntimeException {

    public CommunicationDeliveryException(String message) {
        super(message);
    }
}
