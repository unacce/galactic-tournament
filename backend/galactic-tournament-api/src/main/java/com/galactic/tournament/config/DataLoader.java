package com.galactic.tournament.config;

import com.galactic.tournament.model.Species;
import com.galactic.tournament.repository.SpeciesRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final SpeciesRepository speciesRepository;

    public DataLoader(
            SpeciesRepository speciesRepository) {

        this.speciesRepository = speciesRepository;
    }

    @Override
    public void run(String... args) {

        if (!speciesRepository.findAll().isEmpty()) {
            return;
        }

        createSpecies(
                "Superman",
                9000,
                "Volar-Fuerza-Velocidad-Rayos ojos");

        createSpecies(
                "Omniman",
                7000,
                "Volar-Fuerza-Velocidad");

        createSpecies(
                "Goku",
                5000,
                "Volar-Fuerza-Velocidad-Transformaciones");

        createSpecies(
                "Zaitama",
                9000,
                "Super Fuerza-Super Resistencia");
    }

    private void createSpecies(
            String name,
            Integer powerLevel,
            String ability) {

        Species species = new Species();

        species.setName(name);
        species.setPowerLevel(powerLevel);
        species.setSpecialAbility(ability);
        species.setVictories(0);

        speciesRepository.save(species);
    }
}