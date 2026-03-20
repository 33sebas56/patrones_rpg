package com.sebas.rpg.model.event;

import com.sebas.rpg.model.enumtype.BuffType;

public class EventOutcome {

    private final String title;
    private final String description;
    private final BuffType appliedEffect;

    public EventOutcome(String title, String description, BuffType appliedEffect) {
        this.title = title;
        this.description = description;
        this.appliedEffect = appliedEffect;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public BuffType getAppliedEffect() {
        return appliedEffect;
    }
}