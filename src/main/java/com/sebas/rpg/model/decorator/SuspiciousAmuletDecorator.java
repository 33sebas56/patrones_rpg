package com.sebas.rpg.model.decorator;

import com.sebas.rpg.model.base.Hero;

public class SuspiciousAmuletDecorator extends HeroDecorator {

    public SuspiciousAmuletDecorator(Hero hero) {
        super(hero);
    }

    @Override
    public String getDescription() {
        return hero.getDescription() + ", Suspicious Amulet";
    }

    @Override
    public int getCritChance() {
        return hero.getCritChance() + 8;
    }

    @Override
    public int getLuck() {
        return hero.getLuck() - 3;
    }
}