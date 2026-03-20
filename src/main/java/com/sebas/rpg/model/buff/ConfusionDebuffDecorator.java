package com.sebas.rpg.model.buff;

import com.sebas.rpg.model.base.Hero;
import com.sebas.rpg.model.decorator.HeroDecorator;

public class ConfusionDebuffDecorator extends HeroDecorator {

    public ConfusionDebuffDecorator(Hero hero) {
        super(hero);
    }

    @Override
    public String getDescription() {
        return hero.getDescription() + ", Confusion";
    }

    @Override
    public int getAttack() {
        return hero.getAttack() - 4;
    }

    @Override
    public int getCritChance() {
        return hero.getCritChance() - 3;
    }
}