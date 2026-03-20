package com.sebas.rpg.dto.response;

import com.sebas.rpg.model.enumtype.ChoiceType;
import com.sebas.rpg.model.enumtype.EventType;

import java.util.List;

public class RandomEventResponse {

    private EventType eventType;
    private String title;
    private String description;
    private List<ChoiceType> availableChoices;

    public RandomEventResponse() {
    }

    public RandomEventResponse(
            EventType eventType,
            String title,
            String description,
            List<ChoiceType> availableChoices
    ) {
        this.eventType = eventType;
        this.title = title;
        this.description = description;
        this.availableChoices = availableChoices;
    }

    public EventType getEventType() {
        return eventType;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public List<ChoiceType> getAvailableChoices() {
        return availableChoices;
    }
}