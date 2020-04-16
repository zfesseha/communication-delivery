package com.chronicle.communications.common.model;

import com.chronicle.communications.common.model.enums.RecipientType;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.UUID;

@Value.Immutable
@JsonSerialize(as = ImmutableRecipient.class)
@JsonDeserialize(as = ImmutableRecipient.class)
public interface Recipient {
    UUID id();
    UUID communicationId();
    RecipientType type();
    String identifier();
    Optional<OffsetDateTime> createdAt();
}
