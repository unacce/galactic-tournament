package com.galactic.tournament.service;

import com.galactic.tournament.dto.CreateSpeciesRequest;
import com.galactic.tournament.model.Species;
import com.galactic.tournament.repository.SpeciesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SpeciesServiceTest {

        private SpeciesRepository repository;

        private SpeciesService service;

        @BeforeEach
        void setup() {

                repository = new SpeciesRepository();

                service = new SpeciesService(repository);
        }

        @Test
        void create_should_create_species_successfully() {

                // Arrange

                CreateSpeciesRequest request = new CreateSpeciesRequest();

                request.setName("Saiyayin");
                request.setPowerLevel(9000);
                request.setSpecialAbility(
                                "Super Saiyayin");

                // Act

                Species result = service.create(request);

                // Assert

                assertNotNull(result);

                assertEquals(
                                "Saiyayin",
                                result.getName());

                assertEquals(
                                9000,
                                result.getPowerLevel());

                assertEquals(
                                "Super Saiyayin",
                                result.getSpecialAbility());

                assertEquals(
                                0,
                                result.getVictories());
        }
}