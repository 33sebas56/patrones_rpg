package com.sebas.rpg.model.decorator;

import com.sebas.rpg.model.base.Hero;

public class IronArmorDecorator extends HeroDecorator {

    public IronArmorDecorator(Hero hero) {
        super(hero);
    }

    @Override
    public String getDescription() {
        return hero.getDescription() + ", Iron Armor";
    }

    @Override
    public int getHealth() {
        return hero.getHealth() + 20;
    }

    @Override
    public int getDefense() {
        return hero.getDefense() + 10;
    }

    @Override
    public int getSpeed() {
        return hero.getSpeed() - 2;
    }
}