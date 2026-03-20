package com.sebas.rpg.model.buff;

import com.sebas.rpg.model.base.Hero;
import com.sebas.rpg.model.decorator.HeroDecorator;

public class CoffeeRushBuffDecorator extends HeroDecorator {

    public CoffeeRushBuffDecorator(Hero hero) {
        super(hero);
    }

    @Override
    public String getDescription() {
        return hero.getDescription() + ", Coffee Rush";
    }

    @Override
    public int getAttack() {
        return hero.getAttack() + 4;
    }

    @Override
    public int getSpeed() {
        return hero.getSpeed() + 6;
    }
}