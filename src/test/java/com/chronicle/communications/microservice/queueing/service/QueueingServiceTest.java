package com.chronicle.communications.microservice.queueing.service;

import com.chronicle.communications.common.config.BaseConfig;
import com.chronicle.communications.common.config.JacksonConfig;
import com.chronicle.communications.common.model.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
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

    // TODO: Update with actual test.
    @Test
    public void service() throws Exception {
        UUID communicationId = UUID.randomUUID();
        Communication communication = ImmutableCommunication.builder()
                .id(communicationId)
                .sender("sender@gmail.com")
                .content("This is the content")
                .createdAt(OffsetDateTime.now())
                .build();

        CommunicationRequest request = ImmutableCommunicationRequest.builder()
                .clientId(UUID.randomUUID())
                .communicationId(UUID.randomUUID())
                .type(EMAIL)
                .content("Request Content")
                .segmentId("requestSegmentId")
                .batchId(UUID.randomUUID())
                .recipients(ImmutableSet.of("abc@gmail.com", "123@email.com"))
                .source("API Request")
                .metadata(ImmutableMap.of("key", "value", "meta", "data"))
                .requestTime(OffsetDateTime.now().minusDays(1).minusHours(3))
                .createdAt(OffsetDateTime.now())
                .build();
        // TODO: Remove
        System.out.println(objectMapper.writeValueAsString(communication));
        System.out.println(objectMapper.writeValueAsString(request));
    }
}