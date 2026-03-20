package com.sebas.rpg.model.buff;

import com.sebas.rpg.model.base.Hero;
import com.sebas.rpg.model.decorator.HeroDecorator;

public class ExistentialCrisisDebuffDecorator extends HeroDecorator {

    public ExistentialCrisisDebuffDecorator(Hero hero) {
        super(hero);
    }

    @Override
    public String getDescription() {
        return hero.getDescription() + ", Existential Crisis";
    }

    @Override
    public int getAttack() {
        return hero.getAttack() - 5;
    }

    @Override
    public int getDefense() {
        return hero.getDefense() - 5;
    }
}