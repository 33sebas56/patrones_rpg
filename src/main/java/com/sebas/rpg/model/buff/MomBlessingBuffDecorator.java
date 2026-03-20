package com.sebas.rpg.model.buff;

import com.sebas.rpg.model.base.Hero;
import com.sebas.rpg.model.decorator.HeroDecorator;

public class MomBlessingBuffDecorator extends HeroDecorator {

    public MomBlessingBuffDecorator(Hero hero) {
        super(hero);
    }

    @Override
    public String getDescription() {
        return hero.getDescription() + ", Mom Blessing";
    }

    @Override
    public int getHealth() {
        return hero.getHealth() + 15;
    }

    @Override
    public int getDefense() {
        return hero.getDefense() + 5;
    }
}