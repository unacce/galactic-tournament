package com.galactic.tournament.service;

import com.galactic.tournament.dto.BattleResponse;
import com.galactic.tournament.dto.CreateBattleRequest;
import com.galactic.tournament.dto.CreateSpeciesRequest;
import com.galactic.tournament.exception.BusinessException;
import com.galactic.tournament.exception.ResourceNotFoundException;
import com.galactic.tournament.repository.BattleRepository;
import com.galactic.tournament.repository.SpeciesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BattleServiceTest {

        private SpeciesRepository speciesRepository;

        private BattleRepository battleRepository;

        private SpeciesService speciesService;

        private BattleService battleService;

        @BeforeEach
        void setup() {

                speciesRepository = new SpeciesRepository();

                battleRepository = new BattleRepository();

                speciesService = new SpeciesService(speciesRepository);

                battleService = new BattleService(
                                speciesRepository,
                                battleRepository);
        }

        @Test
        void createBattle_should_select_higher_power_species_as_winner() {

                // Arrange

                CreateSpeciesRequest saiyan = new CreateSpeciesRequest();

                saiyan.setName("Goku");
                saiyan.setPowerLevel(9000);
                saiyan.setSpecialAbility("Super Saiyayin");

                CreateSpeciesRequest kryptonian = new CreateSpeciesRequest();

                kryptonian.setName("Super Man");
                kryptonian.setPowerLevel(7000);
                kryptonian.setSpecialAbility("Vision Laser");

                speciesService.create(saiyan);
                speciesService.create(kryptonian);

                CreateBattleRequest battleRequest = new CreateBattleRequest();

                battleRequest.setSpeciesOneId(1L);
                battleRequest.setSpeciesTwoId(2L);

                // Act

                BattleResponse response = battleService.createBattle(
                                battleRequest);

                // Assert

                assertEquals(
                                "Goku",
                                response.getWinner());
        }

        @Test
        void createBattle_should_select_alphabetically_first_species_when_power_is_equal() {

                // Arrange

                CreateSpeciesRequest andromedan = new CreateSpeciesRequest();

                andromedan.setName("Andromeda");
                andromedan.setPowerLevel(5000);
                andromedan.setSpecialAbility("Teletransportacion");

                CreateSpeciesRequest saiyan = new CreateSpeciesRequest();

                saiyan.setName("Saiyayin");
                saiyan.setPowerLevel(5000);
                saiyan.setSpecialAbility("Super Saiyayin");

                speciesService.create(andromedan);
                speciesService.create(saiyan);

                CreateBattleRequest battleRequest = new CreateBattleRequest();

                battleRequest.setSpeciesOneId(1L);
                battleRequest.setSpeciesTwoId(2L);

                // Act

                BattleResponse response = battleService.createBattle(
                                battleRequest);

                // Assert

                assertEquals(
                                "Andromeda",
                                response.getWinner());
        }

        @Test
        void createBattle_should_throw_business_exception_when_same_species() {

                // Arrange

                CreateSpeciesRequest saiyan = new CreateSpeciesRequest();

                saiyan.setName("Saiyayin");
                saiyan.setPowerLevel(9000);
                saiyan.setSpecialAbility("Super Saiyayin");

                speciesService.create(saiyan);

                CreateBattleRequest request = new CreateBattleRequest();

                request.setSpeciesOneId(1L);
                request.setSpeciesTwoId(1L);

                // Act + Assert

                BusinessException exception = assertThrows(
                                BusinessException.class,
                                () -> battleService.createBattle(request));

                assertEquals(
                                "Una batalla requiere dos especies diferentes.",
                                exception.getMessage());
        }

        @Test
        void createBattle_should_throw_not_found_exception() {

                // Arrange

                CreateSpeciesRequest saiyan = new CreateSpeciesRequest();

                saiyan.setName("Saiyayin");
                saiyan.setPowerLevel(9000);
                saiyan.setSpecialAbility("Super Saiyayin");

                speciesService.create(saiyan);

                CreateBattleRequest request = new CreateBattleRequest();

                request.setSpeciesOneId(99L);
                request.setSpeciesTwoId(1L);

                // Act + Assert

                ResourceNotFoundException exception = assertThrows(
                                ResourceNotFoundException.class,
                                () -> battleService.createBattle(request));

                assertEquals(
                                "No se encontró la especie con el identificador 99",
                                exception.getMessage());
        }
}