package com.chronicle.communications.microservice.communication.controller;

import com.chronicle.communications.common.exception.NotFoundException;
import com.chronicle.communications.common.model.Communication;
import com.chronicle.communications.microservice.communication.service.CommunicationService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/communications-api/v1/communications")
public class CommunicationsController {

    private CommunicationService communicationService;

    public CommunicationsController(CommunicationService communicationService) {
        this.communicationService = communicationService;
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Communication> getAll() {
        return communicationService.getAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Communication getAll(@PathVariable UUID id) {
        return communicationService.get(id).orElseThrow(() -> new NotFoundException(id, "Communication"));
    }
}
