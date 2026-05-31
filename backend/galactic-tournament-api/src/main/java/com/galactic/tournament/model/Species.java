package com.galactic.tournament.model;

public class Species {

    private Long id;
    private String name;
    private Integer powerLevel;
    private String specialAbility;
    private Integer victories;

    public Species() {
    }

    public Species(
            Long id,
            String name,
            Integer powerLevel,
            String specialAbility,
            Integer victories) {

        this.id = id;
        this.name = name;
        this.powerLevel = powerLevel;
        this.specialAbility = specialAbility;
        this.victories = victories;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Integer getPowerLevel() {
        return powerLevel;
    }

    public String getSpecialAbility() {
        return specialAbility;
    }

    public Integer getVictories() {
        return victories;
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

    public void setVictories(Integer victories) {
        this.victories = victories;
    }
}