package com.sebas.rpg.model.stats;

public class HeroStats {

    private final int health;
    private final int attack;
    private final int defense;
    private final int speed;
    private final int critChance;
    private final int luck;

    public HeroStats(int health, int attack, int defense, int speed, int critChance, int luck) {
        this.health = health;
        this.attack = attack;
        this.defense = defense;
        this.speed = speed;
        this.critChance = critChance;
        this.luck = luck;
    }

    public int getHealth() {
        return health;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public int getSpeed() {
        return speed;
    }

    public int getCritChance() {
        return critChance;
    }

    public int getLuck() {
        return luck;
    }
}