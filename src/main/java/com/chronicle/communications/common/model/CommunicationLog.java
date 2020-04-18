package com.chronicle.communications.common.model;

import com.chronicle.communications.common.model.enums.CommunicationEvent;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

import java.time.OffsetDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Value.Immutable
@JsonSerialize(as = ImmutableCommunicationLog.class)
@JsonDeserialize(as = ImmutableCommunicationLog.class)
public interface CommunicationLog {
    UUID id();
    UUID communicationId();
    CommunicationEvent event();
    Optional<Map<String, String>> metadata();
    Optional<OffsetDateTime> createdAt();
}
