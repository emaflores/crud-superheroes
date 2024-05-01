package com.emaflores.crudsuperheroes.service;

import com.emaflores.crudsuperheroes.model.SuperHero;

import java.util.List;

public interface SuperHeroService {
    List<SuperHero> getAllSuperHeroes();
    SuperHero getSuperHeroById(Long id);
    List<SuperHero> searchSuperHeroesByName(String name);
    SuperHero createSuperHero(SuperHero superHero);
    SuperHero updateSuperHero(Long id, SuperHero superHero);
    void deleteSuperHero(Long id);
}

