package com.sebas.rpg.service;

import org.springframework.stereotype.Service;

@Service
public class NarrationService {

    public String battleStart(String heroName, String enemyName) {
        return "Battle starts: " + heroName + " vs " + enemyName + "\n\n";
    }

    public String roundHeader(int round) {
        return "Round " + round + ":\n";
    }

    public String heroAttack(String heroName, String enemyName, int damage, int enemyHp) {
        return heroName + " hits " + enemyName + " for " + damage + " damage. Enemy HP: " + enemyHp + "\n";
    }

    public String heroCounter(String heroName, int damage, int enemyHp) {
        return heroName + " responds for " + damage + " damage. Enemy HP: " + enemyHp + "\n";
    }

    public String enemyAttack(String enemyName, int damage, int heroHp) {
        return enemyName + " attacks first for " + damage + " damage. Hero HP: " + heroHp + "\n";
    }

    public String enemyCounter(String enemyName, int damage, int heroHp) {
        return enemyName + " strikes back for " + damage + " damage. Hero HP: " + heroHp + "\n";
    }

    public String winner(String winnerName) {
        return "\nWinner: " + winnerName;
    }
}