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
    private final NarrationService narrationService;
    private final Random random;

    public BattleService(
            HeroBuilderService heroBuilderService,
            EnemyFactory enemyFactory,
            NarrationService narrationService
    ) {
        this.heroBuilderService = heroBuilderService;
        this.enemyFactory = enemyFactory;
        this.narrationService = narrationService;
        this.random = new Random();
    }

    public BattleResultResponse simulateBattle(String enemyName) {
        Hero hero = heroBuilderService.getCurrentHeroEntity();
        Enemy enemy = enemyFactory.createEnemy(enemyName);

        int heroCurrentHealth = hero.getHealth();
        int enemyCurrentHealth = enemy.getHealth();

        StringBuilder log = new StringBuilder();
        log.append(narrationService.battleStart(hero.getName(), enemy.getName()));

        boolean heroTurn = hero.getSpeed() >= enemy.getSpeed();
        int round = 1;

        while (heroCurrentHealth > 0 && enemyCurrentHealth > 0) {
            log.append(narrationService.roundHeader(round));

            if (heroTurn) {
                int heroDamage = calculateHeroDamage(hero, enemy);
                enemyCurrentHealth = Math.max(0, enemyCurrentHealth - heroDamage);
                log.append(narrationService.heroAttack(hero.getName(), enemy.getName(), heroDamage, enemyCurrentHealth));

                if (enemyCurrentHealth <= 0) {
                    break;
                }

                int enemyDamage = calculateEnemyDamage(enemy, hero);
                heroCurrentHealth = Math.max(0, heroCurrentHealth - enemyDamage);
                log.append(narrationService.enemyCounter(enemy.getName(), enemyDamage, heroCurrentHealth));
            } else {
                int enemyDamage = calculateEnemyDamage(enemy, hero);
                heroCurrentHealth = Math.max(0, heroCurrentHealth - enemyDamage);
                log.append(narrationService.enemyAttack(enemy.getName(), enemyDamage, heroCurrentHealth));

                if (heroCurrentHealth <= 0) {
                    break;
                }

                int heroDamage = calculateHeroDamage(hero, enemy);
                enemyCurrentHealth = Math.max(0, enemyCurrentHealth - heroDamage);
                log.append(narrationService.heroCounter(hero.getName(), heroDamage, enemyCurrentHealth));
            }

            log.append("\n");
            round++;

            if (round > 20) {
                throw new BattleSimulationException("Battle exceeded safe round limit");
            }
        }

        String winner = heroCurrentHealth > 0 ? hero.getName() : enemy.getName();
        log.append(narrationService.winner(winner));

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