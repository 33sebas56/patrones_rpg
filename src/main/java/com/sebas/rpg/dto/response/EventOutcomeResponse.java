package com.sebas.rpg.dto.response;

public class EventOutcomeResponse {

    private String title;
    private String description;
    private String appliedEffect;

    public EventOutcomeResponse() {
    }

    public EventOutcomeResponse(String title, String description, String appliedEffect) {
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

    public String getAppliedEffect() {
        return appliedEffect;
    }
}