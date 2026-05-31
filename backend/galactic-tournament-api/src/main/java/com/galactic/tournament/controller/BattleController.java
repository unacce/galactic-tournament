package com.galactic.tournament.controller;

import com.galactic.tournament.dto.BattleResponse;
import com.galactic.tournament.dto.CreateBattleRequest;
import com.galactic.tournament.service.BattleService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import com.galactic.tournament.dto.BattleHistoryResponse;
import java.util.List;

@RestController
@RequestMapping("/api/battles")
@CrossOrigin(origins = "*")
public class BattleController {

    private final BattleService service;

    public BattleController(BattleService service) {
        this.service = service;
    }

    @PostMapping
    public BattleResponse createBattle(
            @Valid @RequestBody CreateBattleRequest request) {

        return service.createBattle(request);
    }

    @GetMapping
    public List<BattleHistoryResponse> getHistory() {

        return service.getHistory();
    }

    @PostMapping("/random")
    public BattleResponse createRandomBattle() {

        return service.createRandomBattle();

    }
}