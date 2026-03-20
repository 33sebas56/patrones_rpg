package com.sebas.rpg.model.enemy;

public class Enemy {

    private final String name;
    private final int health;
    private final int attack;
    private final int defense;
    private final int speed;
    private final int critChance;

    public Enemy(String name, int health, int attack, int defense, int speed, int critChance) {
        this.name = name;
        this.health = health;
        this.attack = attack;
        this.defense = defense;
        this.speed = speed;
        this.critChance = critChance;
    }

    public String getName() {
        return name;
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
}