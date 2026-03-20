package com.sebas.rpg.model.decorator;

import com.sebas.rpg.model.base.Hero;

public class CardboardArmorDecorator extends HeroDecorator {

    public CardboardArmorDecorator(Hero hero) {
        super(hero);
    }

    @Override
    public String getDescription() {
        return hero.getDescription() + ", Cardboard Armor";
    }

    @Override
    public int getDefense() {
        return hero.getDefense() + 2;
    }

    @Override
    public int getLuck() {
        return hero.getLuck() - 1;
    }
}