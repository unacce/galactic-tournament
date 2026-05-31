package com.galactic.tournament.model;

import java.time.LocalDateTime;

public class Battle {

    private Long id;
    private Long speciesOneId;
    private Long speciesTwoId;
    private Long winnerId;
    private LocalDateTime battleDate;

    public Battle() {
    }

    public Long getId() {
        return id;
    }

    public Long getSpeciesOneId() {
        return speciesOneId;
    }

    public Long getSpeciesTwoId() {
        return speciesTwoId;
    }

    public Long getWinnerId() {
        return winnerId;
    }

    public LocalDateTime getBattleDate() {
        return battleDate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setSpeciesOneId(Long speciesOneId) {
        this.speciesOneId = speciesOneId;
    }

    public void setSpeciesTwoId(Long speciesTwoId) {
        this.speciesTwoId = speciesTwoId;
    }

    public void setWinnerId(Long winnerId) {
        this.winnerId = winnerId;
    }

    public void setBattleDate(LocalDateTime battleDate) {
        this.battleDate = battleDate;
    }
}