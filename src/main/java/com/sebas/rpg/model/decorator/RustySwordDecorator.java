package com.sebas.rpg.model.decorator;

import com.sebas.rpg.model.base.Hero;

public class RustySwordDecorator extends HeroDecorator {

    public RustySwordDecorator(Hero hero) {
        super(hero);
    }

    @Override
    public String getDescription() {
        return hero.getDescription() + ", Rusty Sword";
    }

    @Override
    public int getAttack() {
        return hero.getAttack() + 8;
    }

    @Override
    public int getLuck() {
        return hero.getLuck() - 2;
    }
}