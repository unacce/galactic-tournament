package com.galactic.tournament.dto;

import jakarta.validation.constraints.NotNull;

public class CreateBattleRequest {

    @NotNull
    private Long speciesOneId;

    @NotNull
    private Long speciesTwoId;

    public Long getSpeciesOneId() {
        return speciesOneId;
    }

    public Long getSpeciesTwoId() {
        return speciesTwoId;
    }

    public void setSpeciesOneId(Long speciesOneId) {
        this.speciesOneId = speciesOneId;
    }

    public void setSpeciesTwoId(Long speciesTwoId) {
        this.speciesTwoId = speciesTwoId;
    }
}