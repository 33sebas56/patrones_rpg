package com.sebas.rpg.model.decorator;

import com.sebas.rpg.model.base.Hero;

public class LegendarySandalDecorator extends HeroDecorator {

    public LegendarySandalDecorator(Hero hero) {
        super(hero);
    }

    @Override
    public String getDescription() {
        return hero.getDescription() + ", Legendary Sandal";
    }

    @Override
    public int getAttack() {
        return hero.getAttack() + 12;
    }

    @Override
    public int getCritChance() {
        return hero.getCritChance() + 6;
    }
}