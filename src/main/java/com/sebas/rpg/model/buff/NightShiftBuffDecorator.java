package com.sebas.rpg.model.buff;

import com.sebas.rpg.model.base.Hero;
import com.sebas.rpg.model.decorator.HeroDecorator;

public class NightShiftBuffDecorator extends HeroDecorator {

    public NightShiftBuffDecorator(Hero hero) {
        super(hero);
    }

    @Override
    public String getDescription() {
        return hero.getDescription() + ", Night Shift";
    }

    @Override
    public int getAttack() {
        return hero.getAttack() + 5;
    }

    @Override
    public int getHealth() {
        return hero.getHealth() - 8;
    }
}