package com.emaflores.crudsuperheroes.controller;

import com.emaflores.crudsuperheroes.annotation.CustomTimer;
import com.emaflores.crudsuperheroes.exception.SuperHeroNotFoundException;
import com.emaflores.crudsuperheroes.model.SuperHero;
import com.emaflores.crudsuperheroes.service.SuperHeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/superheroes")
public class SuperHeroController {

    @Autowired
    private SuperHeroService service;

    @CustomTimer
    @GetMapping("/")
    public List<SuperHero> getAllSuperHeroes() {
        return service.getAllSuperHeroes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SuperHero> getSuperHeroById(@PathVariable Long id) {
        SuperHero superHero = service.getSuperHeroById(id);
        if (superHero == null) {
            throw new SuperHeroNotFoundException("SuperHero with ID " + id + " not found");
        }
        return new ResponseEntity<>(superHero, HttpStatus.OK);
    }

    @GetMapping("/search")
    public List<SuperHero> searchSuperHeroesByName(@RequestParam String name) {
        return service.searchSuperHeroesByName(name);
    }

    @PostMapping("/")
    public SuperHero createSuperHero(@RequestBody SuperHero superHero) {
        return service.createSuperHero(superHero);
    }

    @PutMapping("/{id}")
    public SuperHero updateSuperHero(@PathVariable Long id, @RequestBody SuperHero superHero) {
        return service.updateSuperHero(id, superHero);
    }

    @DeleteMapping("/{id}")
    public void deleteSuperHero(@PathVariable Long id) {
        service.deleteSuperHero(id);
    }
}