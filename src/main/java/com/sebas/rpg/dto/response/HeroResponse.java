package com.sebas.rpg.dto.response;

public class HeroResponse {

    private String name;
    private String description;
    private int health;
    private int attack;
    private int defense;
    private int speed;
    private int critChance;
    private int luck;

    public HeroResponse() {
    }

    public HeroResponse(
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

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
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