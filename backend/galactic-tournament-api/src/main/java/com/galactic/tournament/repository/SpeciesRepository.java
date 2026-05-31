package com.galactic.tournament.repository;

import com.galactic.tournament.model.Species;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class SpeciesRepository {

    private final ConcurrentHashMap<Long, Species> storage = new ConcurrentHashMap<>();

    private final AtomicLong sequence = new AtomicLong(1);

    public Species save(Species species) {

        if (species.getId() == null) {
            species.setId(sequence.getAndIncrement());
        }

        storage.put(species.getId(), species);

        return species;
    }

    public List<Species> findAll() {
        return new ArrayList<>(storage.values());
    }

    public Species findById(Long id) {
        return storage.get(id);
    }

    public Species findByName(String name) {

        return storage.values()
                .stream()
                .filter(x -> x.getName()
                        .equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }
}