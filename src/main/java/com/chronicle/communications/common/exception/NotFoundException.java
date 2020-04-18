package com.chronicle.communications.common.exception;

import java.util.UUID;

import static org.springframework.util.StringUtils.isEmpty;

public class NotFoundException extends RuntimeException {

    public NotFoundException(UUID id, String resource) {
        this(id, resource, null);
    }

    public NotFoundException(UUID id, String resource, String message) {
        super(String.format("%s with ID(%s) cannot be found.%s", resource, id, isEmpty(message) ? "" : " " + message));
    }
}
