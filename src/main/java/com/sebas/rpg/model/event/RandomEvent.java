package com.sebas.rpg.model.event;

import com.sebas.rpg.model.enumtype.ChoiceType;
import com.sebas.rpg.model.enumtype.EventType;

import java.util.List;

public class RandomEvent {

    private final EventType eventType;
    private final String title;
    private final String description;
    private final List<ChoiceType> availableChoices;

    public RandomEvent(
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