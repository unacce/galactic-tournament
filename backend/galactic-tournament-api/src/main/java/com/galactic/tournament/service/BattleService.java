package com.galactic.tournament.service;

import com.galactic.tournament.dto.BattleResponse;
import com.galactic.tournament.dto.CreateBattleRequest;
import com.galactic.tournament.exception.BusinessException;
import com.galactic.tournament.exception.ResourceNotFoundException;
import com.galactic.tournament.model.Battle;
import com.galactic.tournament.model.Species;
import com.galactic.tournament.repository.BattleRepository;
import com.galactic.tournament.repository.SpeciesRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import com.galactic.tournament.dto.BattleHistoryResponse;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Random;

@Service
public class BattleService {

        private final SpeciesRepository speciesRepository;
        private final BattleRepository battleRepository;

        public BattleService(
                        SpeciesRepository speciesRepository,
                        BattleRepository battleRepository) {

                this.speciesRepository = speciesRepository;
                this.battleRepository = battleRepository;
        }

        public BattleResponse createBattle(
                        CreateBattleRequest request) {

                if (request.getSpeciesOneId()
                                .equals(request.getSpeciesTwoId())) {

                        throw new BusinessException(
                                        "Una batalla requiere dos especies diferentes.");

                }

                Species speciesOne = speciesRepository.findById(request.getSpeciesOneId());

                Species speciesTwo = speciesRepository.findById(request.getSpeciesTwoId());

                if (speciesOne == null) {
                        throw new ResourceNotFoundException(
                                        "No se encontró la especie con el identificador "
                                                        + request.getSpeciesOneId());
                }

                if (speciesTwo == null) {
                        throw new ResourceNotFoundException(
                                        "No se encontró la especie con el identificador "
                                                        + request.getSpeciesTwoId());
                }

                return executeBattle(
                                speciesOne,
                                speciesTwo);
        }

        private Species determineWinner(
                        Species speciesOne,
                        Species speciesTwo) {

                if (speciesOne.getPowerLevel() > speciesTwo.getPowerLevel()) {
                        return speciesOne;
                }

                if (speciesTwo.getPowerLevel() > speciesOne.getPowerLevel()) {
                        return speciesTwo;
                }

                return speciesOne.getName()
                                .compareToIgnoreCase(
                                                speciesTwo.getName()) <= 0
                                                                ? speciesOne
                                                                : speciesTwo;
        }

        private BattleResponse executeBattle(
                        Species speciesOne,
                        Species speciesTwo) {

                Species winner = determineWinner(
                                speciesOne,
                                speciesTwo);

                winner.setVictories(
                                winner.getVictories() + 1);

                speciesRepository.save(winner);

                Battle battle = new Battle();

                battle.setSpeciesOneId(
                                speciesOne.getId());

                battle.setSpeciesTwoId(
                                speciesTwo.getId());

                battle.setWinnerId(
                                winner.getId());

                battle.setBattleDate(
                                LocalDateTime.now());

                battleRepository.save(battle);

                BattleResponse response = new BattleResponse();

                response.setBattleId(
                                battle.getId());

                response.setSpeciesOne(
                                speciesOne.getName());

                response.setSpeciesTwo(
                                speciesTwo.getName());

                response.setWinner(
                                winner.getName());

                return response;
        }

        public List<BattleHistoryResponse> getHistory() {

                return battleRepository.findAll()
                                .stream()
                                .map(battle -> {

                                        Species speciesOne = speciesRepository.findById(
                                                        battle.getSpeciesOneId());

                                        Species speciesTwo = speciesRepository.findById(
                                                        battle.getSpeciesTwoId());

                                        Species winner = speciesRepository.findById(
                                                        battle.getWinnerId());

                                        BattleHistoryResponse response = new BattleHistoryResponse();

                                        response.setBattleId(
                                                        battle.getId());

                                        response.setSpeciesOne(
                                                        speciesOne.getName());

                                        response.setSpeciesTwo(
                                                        speciesTwo.getName());

                                        response.setWinner(
                                                        winner.getName());

                                        response.setBattleDate(
                                                        battle.getBattleDate());

                                        return response;
                                })
                                .collect(Collectors.toList());
        }

        public BattleResponse createRandomBattle() {

                List<Species> species = speciesRepository.findAll();

                if (species.size() < 2) {

                        throw new BusinessException(
                                        "Se requieren al menos dos especies.");

                }

                Random random = new Random();

                Species speciesOne = species.get(
                                random.nextInt(
                                                species.size()));

                Species speciesTwo;

                do {

                        speciesTwo = species.get(
                                        random.nextInt(
                                                        species.size()));

                } while (speciesOne.getId()
                                .equals(
                                                speciesTwo.getId()));

                return executeBattle(
                                speciesOne,
                                speciesTwo);
        }
}