package com.sebas.rpg.model.decorator;

import com.sebas.rpg.model.base.Hero;

public class BudgetShieldDecorator extends HeroDecorator {

    public BudgetShieldDecorator(Hero hero) {
        super(hero);
    }

    @Override
    public String getDescription() {
        return hero.getDescription() + ", Budget Shield";
    }

    @Override
    public int getDefense() {
        return hero.getDefense() + 6;
    }

    @Override
    public int getSpeed() {
        return hero.getSpeed() - 1;
    }
}