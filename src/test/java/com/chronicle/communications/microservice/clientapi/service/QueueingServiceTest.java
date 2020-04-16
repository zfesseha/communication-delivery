package com.chronicle.communications.microservice.clientapi.service;

import com.chronicle.communications.common.config.BaseConfig;
import com.chronicle.communications.common.config.JacksonConfig;
import com.chronicle.communications.common.model.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.OffsetDateTime;
import java.util.UUID;

import static com.chronicle.communications.common.model.enums.CommunicationType.EMAIL;
import static com.chronicle.communications.common.model.enums.RecipientType.EMAIL_ADDRESS;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {
        BaseConfig.class,
        JacksonConfig.class
})
public class QueueingServiceTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void service() throws Exception {
        UUID communicationId = UUID.randomUUID();
        Communication communication = ImmutableCommunication.builder()
                .id(communicationId)
                .type(EMAIL)
                .sender("sender@gmail.com")
                .content("This is the content")
                .clientId(UUID.randomUUID())
                .segmentId("segmentId")
                .batchId(UUID.randomUUID())
                .recipients(ImmutableList.of(
                        ImmutableRecipient.builder()
                                .id(UUID.randomUUID())
                                .communicationId(communicationId)
                                .type(EMAIL_ADDRESS)
                                .identifier("recipient@gmail.com")
                                .createdAt(OffsetDateTime.now())
                                .build()
                ))
                .createdAt(OffsetDateTime.now())
                .build();
        // UUID id();
        ////    UUID clientId();
        ////    Optional<UUID> communicationId();
        ////    CommunicationType type();
        ////    Optional<String> content();
        ////    Optional<String> segmentId();
        ////    Optional<UUID> batchId();
        ////    Set<String> recipients();
        ////    Optional<String> source();
        ////    Map<String, String> metadata();
        ////    Optional<OffsetDateTime> requestTime();
        ////    Optional<OffsetDateTime> createdAt();
        CommunicationRequest request = ImmutableCommunicationRequest.builder()
                .id(UUID.randomUUID())
//                .clientId(UUID.randomUUID())
//                .communicationId(UUID.randomUUID())
//                .type(EMAIL)
//                .content("Request Content")
//                .segmentId("requestSegmentId")
//                .batchId(UUID.randomUUID())
//                .recipients(ImmutableSet.of("abc@gmail.com", "123@email.com"))
//                .source("API Request")
//                .metadata(ImmutableMap.of("key", "value", "meta", "data"))
//                .requestTime(OffsetDateTime.now().minusDays(1).minusHours(3))
//                .createdAt(OffsetDateTime.now())
                .build();
        // TODO: Remove
        System.out.println(objectMapper.writeValueAsString(communication));
        System.out.println(objectMapper.writeValueAsString(request));
    }
}