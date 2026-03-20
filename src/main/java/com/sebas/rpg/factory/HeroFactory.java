package com.sebas.rpg.factory;

import com.sebas.rpg.model.base.Hero;
import com.sebas.rpg.model.base.InternHero;
import com.sebas.rpg.model.base.MageHero;
import com.sebas.rpg.model.base.RogueHero;
import com.sebas.rpg.model.base.WarriorHero;
import com.sebas.rpg.model.enumtype.HeroClassType;
import org.springframework.stereotype.Component;

@Component
public class HeroFactory {

    public Hero createHero(String name, HeroClassType heroClassType) {
        if (heroClassType == null) {
            throw new IllegalArgumentException("Hero class type cannot be null");
        }

        String finalName = (name == null || name.isBlank()) ? "Unnamed Hero" : name;

        return switch (heroClassType) {
            case WARRIOR -> new WarriorHero(finalName);
            case MAGE -> new MageHero(finalName);
            case ROGUE -> new RogueHero(finalName);
            case INTERN -> new InternHero(finalName);
        };
    }
}