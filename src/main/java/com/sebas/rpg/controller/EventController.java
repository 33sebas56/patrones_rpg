package com.sebas.rpg.controller;

import com.sebas.rpg.dto.request.ResolveEventRequest;
import com.sebas.rpg.dto.response.EventOutcomeResponse;
import com.sebas.rpg.dto.response.RandomEventResponse;
import com.sebas.rpg.service.RandomEventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/events")
public class EventController {

    private final RandomEventService randomEventService;

    public EventController(RandomEventService randomEventService) {
        this.randomEventService = randomEventService;
    }

    @GetMapping("/random")
    public ResponseEntity<RandomEventResponse> getRandomEvent() {
        return ResponseEntity.ok(randomEventService.getRandomEvent());
    }

    @PostMapping("/resolve")
    public ResponseEntity<EventOutcomeResponse> resolveEvent(@RequestBody ResolveEventRequest request) {
        EventOutcomeResponse response = randomEventService.resolveEvent(
                request.getEventType(),
                request.getChoiceType()
        );
        return ResponseEntity.ok(response);
    }
}