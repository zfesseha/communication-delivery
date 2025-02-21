package com.chronicle.communications.common.model;

import com.chronicle.communications.common.model.enums.CommunicationType;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

import java.time.OffsetDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Value.Immutable
@JsonSerialize(as = ImmutableCommunicationRequest.class)
@JsonDeserialize(as = ImmutableCommunicationRequest.class)
public interface CommunicationRequest {
    Optional<UUID> communicationId();
    Optional<UUID> clientId();
    CommunicationType type();
    Optional<String> content();
    Optional<String> segmentId();
    Optional<String> campaignId();
    Optional<UUID> batchId();
    Optional<Set<String>> recipients();
    Optional<String> source();
    Optional<Map<String, String>> metadata();
    Optional<OffsetDateTime> requestTime();
    Optional<OffsetDateTime> createdAt();
}
