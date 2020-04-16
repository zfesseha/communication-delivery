package com.chronicle.communications.microservice.clientapi.controller;

import com.chronicle.communications.common.model.CommunicationRequest;
import com.chronicle.communications.microservice.clientapi.service.CommunicationRequestService;
import com.chronicle.communications.microservice.queueing.QueuingServiceClient;
import com.google.common.collect.ImmutableList;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/clientapi/v1/communications")
// TODO: Add Swagger annotations
// TODO: Change return type of methods
// TODO: Remove print statements.
public class ClientAPIController {

    private CommunicationRequestService communicationRequestService;

    public ClientAPIController(CommunicationRequestService communicationRequestService) {
        this.communicationRequestService = communicationRequestService;
    }

    // TODO: Implement?
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CommunicationRequest> readAll() {
        return ImmutableList.of();
    }

    @PostMapping(
            value = "",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.CREATED)
    public CommunicationRequest create(@RequestBody CommunicationRequest communicationRequest,
                                       @RequestHeader Map<String, String> headers) {
        System.out.println(communicationRequest);
        communicationRequestService.process(communicationRequest);
        return communicationRequest;
    }
}
