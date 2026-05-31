package com.galactic.tournament.service;

import com.galactic.tournament.dto.CreateSpeciesRequest;
import com.galactic.tournament.dto.RankingResponse;
import com.galactic.tournament.exception.BusinessException;
import com.galactic.tournament.model.Species;
import com.galactic.tournament.repository.SpeciesRepository;
import org.springframework.stereotype.Service;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SpeciesService {

    private final SpeciesRepository repository;

    public SpeciesService(SpeciesRepository repository) {
        this.repository = repository;
    }

    public Species create(CreateSpeciesRequest request) {

        Species species = new Species();

        Species existing = repository.findByName(
                request.getName());

        if (existing != null) {

            throw new BusinessException(
                    "El nombre de la especie ya existe.");
        }

        species.setName(request.getName());
        species.setPowerLevel(request.getPowerLevel());
        species.setSpecialAbility(request.getSpecialAbility());

        species.setVictories(0);

        return repository.save(species);
    }

    public List<Species> getAll() {
        return repository.findAll();
    }

    public List<RankingResponse> getRanking() {

        return repository.findAll()
                .stream()
                .sorted(
                        Comparator.comparing(
                                Species::getVictories)
                                .reversed())
                .map(species -> {

                    RankingResponse response = new RankingResponse();

                    response.setId(species.getId());
                    response.setName(species.getName());
                    response.setVictories(
                            species.getVictories());

                    return response;
                })
                .collect(Collectors.toList());
    }
}