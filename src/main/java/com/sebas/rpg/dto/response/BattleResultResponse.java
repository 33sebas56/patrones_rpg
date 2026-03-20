package com.sebas.rpg.dto.response;

public class BattleResultResponse {

    private String heroName;
    private String enemyName;
    private String winner;
    private String battleLog;

    public BattleResultResponse() {
    }

    public BattleResultResponse(String heroName, String enemyName, String winner, String battleLog) {
        this.heroName = heroName;
        this.enemyName = enemyName;
        this.winner = winner;
        this.battleLog = battleLog;
    }

    public String getHeroName() {
        return heroName;
    }

    public String getEnemyName() {
        return enemyName;
    }

    public String getWinner() {
        return winner;
    }

    public String getBattleLog() {
        return battleLog;
    }
}