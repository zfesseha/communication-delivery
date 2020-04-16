package com.chronicle.communications.common.model;

import com.chronicle.communications.common.model.enums.CommunicationType;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Value.Immutable
@JsonSerialize(as = ImmutableCommunicationDTO.class)
@JsonDeserialize(as = ImmutableCommunicationDTO.class)
public interface CommunicationDTO {
    Communication communication();
    CommunicationRequest communicationRequest();
}
