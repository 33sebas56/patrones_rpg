package com.sebas.rpg.factory;

import com.sebas.rpg.model.enemy.CompilerBeast;
import com.sebas.rpg.model.enemy.Enemy;
import com.sebas.rpg.model.enemy.MondayBoss;
import com.sebas.rpg.model.enemy.NullPointerPhantom;
import com.sebas.rpg.model.enemy.TaxCollector;
import org.springframework.stereotype.Component;

@Component
public class EnemyFactory {

    public Enemy createEnemy(String enemyName) {
        if (enemyName == null || enemyName.isBlank()) {
            return new CompilerBeast();
        }

        return switch (enemyName.trim().toUpperCase()) {
            case "COMPILER_BEAST" -> new CompilerBeast();
            case "MONDAY_BOSS" -> new MondayBoss();
            case "NULL_POINTER_PHANTOM" -> new NullPointerPhantom();
            case "TAX_COLLECTOR" -> new TaxCollector();
            default -> new CompilerBeast();
        };
    }
}