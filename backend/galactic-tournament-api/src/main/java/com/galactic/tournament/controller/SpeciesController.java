package com.galactic.tournament.controller;

import com.galactic.tournament.dto.CreateSpeciesRequest;
import com.galactic.tournament.dto.RankingResponse;
import com.galactic.tournament.model.Species;
import com.galactic.tournament.service.SpeciesService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/species")
@CrossOrigin(origins = "*")
public class SpeciesController {

    private final SpeciesService service;

    public SpeciesController(SpeciesService service) {
        this.service = service;
    }

    @PostMapping
    public Species create(
            @Valid @RequestBody CreateSpeciesRequest request) {

        return service.create(request);
    }

    @GetMapping
    public List<Species> getAll() {
        return service.getAll();
    }

    @GetMapping("/ranking")
    public List<RankingResponse> getRanking() {
        return service.getRanking();
    }
}