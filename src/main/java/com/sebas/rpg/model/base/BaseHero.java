package com.sebas.rpg.model.base;

public abstract class BaseHero implements Hero {

    private final String name;
    private final String description;
    private final int health;
    private final int attack;
    private final int defense;
    private final int speed;
    private final int critChance;
    private final int luck;

    protected BaseHero(
            String name,
            String description,
            int health,
            int attack,
            int defense,
            int speed,
            int critChance,
            int luck
    ) {
        this.name = name;
        this.description = description;
        this.health = health;
        this.attack = attack;
        this.defense = defense;
        this.speed = speed;
        this.critChance = critChance;
        this.luck = luck;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public int getAttack() {
        return attack;
    }

    @Override
    public int getDefense() {
        return defense;
    }

    @Override
    public int getSpeed() {
        return speed;
    }

    @Override
    public int getCritChance() {
        return critChance;
    }

    @Override
    public int getLuck() {
        return luck;
    }
}