package com.galactic.tournament.dto;

import java.time.LocalDateTime;

public class BattleHistoryResponse {

    private Long battleId;

    private String speciesOne;

    private String speciesTwo;

    private String winner;

    private LocalDateTime battleDate;

    public Long getBattleId() {
        return battleId;
    }

    public void setBattleId(Long battleId) {
        this.battleId = battleId;
    }

    public String getSpeciesOne() {
        return speciesOne;
    }

    public void setSpeciesOne(String speciesOne) {
        this.speciesOne = speciesOne;
    }

    public String getSpeciesTwo() {
        return speciesTwo;
    }

    public void setSpeciesTwo(String speciesTwo) {
        this.speciesTwo = speciesTwo;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public LocalDateTime getBattleDate() {
        return battleDate;
    }

    public void setBattleDate(LocalDateTime battleDate) {
        this.battleDate = battleDate;
    }
}