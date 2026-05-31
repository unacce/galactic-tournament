package com.galactic.tournament.repository;

import com.galactic.tournament.model.Battle;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class BattleRepository {

    private final ConcurrentHashMap<Long, Battle> storage = new ConcurrentHashMap<>();

    private final AtomicLong sequence = new AtomicLong(1);

    public Battle save(Battle battle) {

        if (battle.getId() == null) {
            battle.setId(sequence.getAndIncrement());
        }

        storage.put(battle.getId(), battle);

        return battle;
    }

    public List<Battle> findAll() {
        return new ArrayList<>(storage.values());
    }
}