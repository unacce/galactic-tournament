package com.galactic.tournament.dto;

public class BattleResponse {

    private Long battleId;

    private String speciesOne;

    private String speciesTwo;

    private String winner;

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
}