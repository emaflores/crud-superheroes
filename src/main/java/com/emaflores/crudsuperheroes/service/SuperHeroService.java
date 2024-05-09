package com.emaflores.crudsuperheroes.service;

import com.emaflores.crudsuperheroes.model.SuperHero;

import java.util.List;
import java.util.Optional;

public interface SuperHeroService {
    List<SuperHero> getAllSuperHeroes();
    Optional<SuperHero> getSuperHeroById(Long id);
    List<SuperHero> searchSuperHeroesByName(String name);
    SuperHero createSuperHero(SuperHero superHero);
    SuperHero updateSuperHero(Long id, SuperHero superHero);
    void deleteSuperHero(Long id);
}

