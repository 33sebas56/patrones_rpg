package com.sebas.rpg.dto.request;

public class BattleRequest {

    private String enemyName;

    public BattleRequest() {
    }

    public BattleRequest(String enemyName) {
        this.enemyName = enemyName;
    }

    public String getEnemyName() {
        return enemyName;
    }

    public void setEnemyName(String enemyName) {
        this.enemyName = enemyName;
    }
}