package com.sebas.rpg.dto.request;

import com.sebas.rpg.model.base.Hero;
import com.sebas.rpg.model.buff.*;
import com.sebas.rpg.model.enumtype.BuffType;
import com.sebas.rpg.model.enumtype.ChoiceType;
import com.sebas.rpg.model.enumtype.EventType;

import static com.sebas.rpg.model.enumtype.BuffType.COFFEE_RUSH;

public class ResolveEventRequest {

    private EventType eventType;
    private ChoiceType choiceType;

    public ResolveEventRequest() {
    }

    public ResolveEventRequest(EventType eventType, ChoiceType choiceType) {
        this.eventType = eventType;
        this.choiceType = choiceType;
    }
    public void applyEffect(BuffType buffType) {
        Hero hero = getCurrentHeroOrThrow();

        currentHero = switch (buffType) {
            case COFFEE_RUSH -> new CoffeeRushBuffDecorator(hero);
            case MOM_BLESSING -> new MomBlessingBuffDecorator(hero);
            case MAIN_CHARACTER -> new MainCharacterBuffDecorator(hero);
            case NIGHT_SHIFT -> new NightShiftBuffDecorator(hero);
            case CONFUSION -> new ConfusionDebuffDecorator(hero);
            case BROKEN_HEART -> new BrokenHeartDebuffDecorator(hero);
            case EXISTENTIAL_CRISIS -> new ExistentialCrisisDebuffDecorator(hero);
        };
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public ChoiceType getChoiceType() {
        return choiceType;
    }

    public void setChoiceType(ChoiceType choiceType) {
        this.choiceType = choiceType;
    }
}