package com.galactic.tournament.dto;

public class RankingResponse {

    private Long id;
    private String name;
    private Integer victories;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getVictories() {
        return victories;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setVictories(Integer victories) {
        this.victories = victories;
    }
}