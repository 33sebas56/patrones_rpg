package com.sebas.rpg.model.decorator;

import com.sebas.rpg.model.base.Hero;

public abstract class HeroDecorator implements Hero {

    protected final Hero hero;

    protected HeroDecorator(Hero hero) {
        this.hero = hero;
    }

    @Override
    public String getName() {
        return hero.getName();
    }

    @Override
    public String getDescription() {
        return hero.getDescription();
    }

    @Override
    public int getHealth() {
        return hero.getHealth();
    }

    @Override
    public int getAttack() {
        return hero.getAttack();
    }

    @Override
    public int getDefense() {
        return hero.getDefense();
    }

    @Override
    public int getSpeed() {
        return hero.getSpeed();
    }

    @Override
    public int getCritChance() {
        return hero.getCritChance();
    }

    @Override
    public int getLuck() {
        return hero.getLuck();
    }
}