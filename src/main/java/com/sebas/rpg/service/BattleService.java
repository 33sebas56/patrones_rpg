package com.sebas.rpg.service;

import com.sebas.rpg.dto.response.BattleResultResponse;
import com.sebas.rpg.exception.BattleSimulationException;
import com.sebas.rpg.factory.EnemyFactory;
import com.sebas.rpg.model.base.Hero;
import com.sebas.rpg.model.enemy.Enemy;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class BattleService {

    private final HeroBuilderService heroBuilderService;
    private final EnemyFactory enemyFactory;
    private final Random random;

    public BattleService(HeroBuilderService heroBuilderService, EnemyFactory enemyFactory) {
        this.heroBuilderService = heroBuilderService;
        this.enemyFactory = enemyFactory;
        this.random = new Random();
    }

    public BattleResultResponse simulateBattle(String enemyName) {
        Hero hero = heroBuilderService.getCurrentHeroEntity();
        Enemy enemy = enemyFactory.createEnemy(enemyName);

        int heroCurrentHealth = hero.getHealth();
        int enemyCurrentHealth = enemy.getHealth();

        StringBuilder log = new StringBuilder();
        log.append("Battle starts: ")
                .append(hero.getName())
                .append(" vs ")
                .append(enemy.getName())
                .append("\n\n");

        boolean heroTurn = hero.getSpeed() >= enemy.getSpeed();

        int round = 1;

        while (heroCurrentHealth > 0 && enemyCurrentHealth > 0) {
            log.append("Round ").append(round).append(":\n");

            if (heroTurn) {
                int damage = calculateHeroDamage(hero, enemy);
                enemyCurrentHealth -= damage;
                if (enemyCurrentHealth < 0) {
                    enemyCurrentHealth = 0;
                }

                log.append(hero.getName())
                        .append(" hits ")
                        .append(enemy.getName())
                        .append(" for ")
                        .append(damage)
                        .append(" damage. Enemy HP: ")
                        .append(enemyCurrentHealth)
                        .append("\n");

                if (enemyCurrentHealth <= 0) {
                    break;
                }

                int enemyDamage = calculateEnemyDamage(enemy, hero);
                heroCurrentHealth -= enemyDamage;
                if (heroCurrentHealth < 0) {
                    heroCurrentHealth = 0;
                }

                log.append(enemy.getName())
                        .append(" strikes back for ")
                        .append(enemyDamage)
                        .append(" damage. Hero HP: ")
                        .append(heroCurrentHealth)
                        .append("\n");
            } else {
                int enemyDamage = calculateEnemyDamage(enemy, hero);
                heroCurrentHealth -= enemyDamage;
                if (heroCurrentHealth < 0) {
                    heroCurrentHealth = 0;
                }

                log.append(enemy.getName())
                        .append(" attacks first for ")
                        .append(enemyDamage)
                        .append(" damage. Hero HP: ")
                        .append(heroCurrentHealth)
                        .append("\n");

                if (heroCurrentHealth <= 0) {
                    break;
                }

                int damage = calculateHeroDamage(hero, enemy);
                enemyCurrentHealth -= damage;
                if (enemyCurrentHealth < 0) {
                    enemyCurrentHealth = 0;
                }

                log.append(hero.getName())
                        .append(" responds for ")
                        .append(damage)
                        .append(" damage. Enemy HP: ")
                        .append(enemyCurrentHealth)
                        .append("\n");
            }

            log.append("\n");
            round++;

            if (round > 20) {
                throw new BattleSimulationException("Battle exceeded safe round limit");
            }
        }

        String winner = heroCurrentHealth > 0 ? hero.getName() : enemy.getName();

        log.append("Winner: ").append(winner);

        return new BattleResultResponse(
                hero.getName(),
                enemy.getName(),
                winner,
                log.toString()
        );
    }

    private int calculateHeroDamage(Hero hero, Enemy enemy) {
        int baseDamage = hero.getAttack() - (enemy.getDefense() / 2);
        int critRoll = random.nextInt(100) + 1;
        boolean criticalHit = critRoll <= hero.getCritChance();

        int luckBonus = random.nextInt(Math.max(1, hero.getLuck() / 2 + 1));
        int totalDamage = Math.max(1, baseDamage + luckBonus);

        if (criticalHit) {
            totalDamage += hero.getAttack() / 2;
        }

        return Math.max(1, totalDamage);
    }

    private int calculateEnemyDamage(Enemy enemy, Hero hero) {
        int baseDamage = enemy.getAttack() - (hero.getDefense() / 2);
        int critRoll = random.nextInt(100) + 1;
        boolean criticalHit = critRoll <= enemy.getCritChance();

        int totalDamage = Math.max(1, baseDamage);

        if (criticalHit) {
            totalDamage += enemy.getAttack() / 2;
        }

        return Math.max(1, totalDamage);
    }
}