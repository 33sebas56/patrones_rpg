package com.sebas.rpg.model.buff;

import com.sebas.rpg.model.base.Hero;
import com.sebas.rpg.model.decorator.HeroDecorator;

public class BrokenHeartDebuffDecorator extends HeroDecorator {

    public BrokenHeartDebuffDecorator(Hero hero) {
        super(hero);
    }

    @Override
    public String getDescription() {
        return hero.getDescription() + ", Broken Heart";
    }

    @Override
    public int getSpeed() {
        return hero.getSpeed() - 3;
    }

    @Override
    public int getDefense() {
        return hero.getDefense() - 2;
    }
}