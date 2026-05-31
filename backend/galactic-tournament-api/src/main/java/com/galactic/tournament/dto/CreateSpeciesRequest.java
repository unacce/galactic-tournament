package com.galactic.tournament.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateSpeciesRequest {

    @NotBlank
    private String name;

    @NotNull
    @Min(0)
    private Integer powerLevel;

    @NotBlank
    private String specialAbility;

    public String getName() {
        return name;
    }

    public Integer getPowerLevel() {
        return powerLevel;
    }

    public String getSpecialAbility() {
        return specialAbility;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPowerLevel(Integer powerLevel) {
        this.powerLevel = powerLevel;
    }

    public void setSpecialAbility(String specialAbility) {
        this.specialAbility = specialAbility;
    }
}