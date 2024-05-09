package com.emaflores.crudsuperheroes.service.impl;

import com.emaflores.crudsuperheroes.model.SuperHero;
import com.emaflores.crudsuperheroes.repository.SuperHeroRepository;
import com.emaflores.crudsuperheroes.service.SuperHeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class SuperHeroServiceImpl implements SuperHeroService {

    private final SuperHeroRepository repository;

    @Autowired
    public SuperHeroServiceImpl(SuperHeroRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<SuperHero> getAllSuperHeroes() {
        return repository.findAll();
    }

    @Override
    public Optional<SuperHero> getSuperHeroById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid ID: ID must be positive and non-null");
        }
        return repository.findById(id);
    }

    @Override
    public List<SuperHero> searchSuperHeroesByName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return repository.findAll();
        } else {
            List<SuperHero> superHeroes = repository.findByNameContainingIgnoreCase(name);
            return superHeroes.isEmpty() ? Collections.emptyList() : superHeroes;
        }
    }


    @Override
    public SuperHero createSuperHero(SuperHero superHero) {
        if (superHero.getName() == null || superHero.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("SuperHero name cannot be empty");
        }

        try {
            Optional<SuperHero> existingHero = repository.findByNameIgnoreCase(superHero.getName());
            if (existingHero.isPresent()) {
                throw new IllegalStateException("SuperHero already exists with this name");
            }
            superHero.setId(null);
            return repository.save(superHero);
        } catch (Exception e) {
            switch (e) {
                case IllegalArgumentException iae -> throw iae;
                case IllegalStateException ise -> throw ise;
                default -> throw new RuntimeException("Unexpected error during superhero creation", e);
            }
        }
    }

    @Override
    public SuperHero updateSuperHero(Long id, SuperHero superHero) {
        if (id <= 0) {
            throw new IllegalArgumentException("Invalid ID: ID must be greater than zero.");
        }
        if (!repository.existsById(id)) {
            throw new IllegalStateException("SuperHero with ID " + id + " does not exist");
        }

        if (superHero.getName() == null || superHero.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("SuperHero name cannot be empty");
        }

        try {
            Optional<SuperHero> existingHero = repository.findByNameIgnoreCase(superHero.getName());
            if (existingHero.isPresent()) {
                throw new IllegalStateException("SuperHero already exists with this name");
            }
            superHero.setId(id);
            return repository.save(superHero);
        } catch (Exception e) {
            switch (e) {
                case IllegalArgumentException iae -> throw iae;
                case IllegalStateException ise -> throw ise;
                default -> throw new RuntimeException("Unexpected error during superhero creation", e);
            }
        }
    }

    @Override
    public void deleteSuperHero(Long id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Invalid ID: ID must be greater than zero.");
        }

        try {
            if (!repository.existsById(id)) {
                throw new IllegalStateException("SuperHero with ID " + id + " does not exist");
            }
            repository.deleteById(id);
        } catch (Exception e) {
            switch (e) {
                case IllegalArgumentException iae -> throw iae;
                case IllegalStateException ise -> throw ise;
                default -> throw new RuntimeException("Unexpected error during superhero deletion", e);
            }
        }
    }

}

