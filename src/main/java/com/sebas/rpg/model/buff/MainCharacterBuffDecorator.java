package com.sebas.rpg.model.buff;

import com.sebas.rpg.model.base.Hero;
import com.sebas.rpg.model.decorator.HeroDecorator;

public class MainCharacterBuffDecorator extends HeroDecorator {

    public MainCharacterBuffDecorator(Hero hero) {
        super(hero);
    }

    @Override
    public String getDescription() {
        return hero.getDescription() + ", Main Character Syndrome";
    }

    @Override
    public int getAttack() {
        return hero.getAttack() + 10;
    }

    @Override
    public int getCritChance() {
        return hero.getCritChance() + 10;
    }
}