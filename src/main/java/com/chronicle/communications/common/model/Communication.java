package com.chronicle.communications.common.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Value.Immutable
@JsonSerialize(as = ImmutableCommunication.class)
@JsonDeserialize(as = ImmutableCommunication.class)
public interface Communication {
    UUID id();
    Optional<String> content();
    // TODO: Should this be an object type or just String?
    Optional<String> sender();
    CommunicationRequest communicationRequest();
    Optional<List<CommunicationLog>> logs();
    Optional<OffsetDateTime> createdAt();
}
