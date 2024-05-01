package com.emaflores.crudsuperheroes.service.impl;

import com.emaflores.crudsuperheroes.model.SuperHero;
import com.emaflores.crudsuperheroes.repository.SuperHeroRepository;
import com.emaflores.crudsuperheroes.service.SuperHeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SuperHeroServiceImpl implements SuperHeroService {

    @Autowired
    private SuperHeroRepository repository;

    @Override
    public List<SuperHero> getAllSuperHeroes() {
        return repository.findAll();
    }

    @Override
    public SuperHero getSuperHeroById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<SuperHero> searchSuperHeroesByName(String name) {
        return repository.findByNameContaining(name);
    }

    @Override
    public SuperHero createSuperHero(SuperHero superHero) {
        return repository.save(superHero);
    }

    @Override
    public SuperHero updateSuperHero(Long id, SuperHero superHero) {
        superHero.setId(id);
        return repository.save(superHero);
    }

    @Override
    public void deleteSuperHero(Long id) {
        repository.deleteById(id);
    }
}

