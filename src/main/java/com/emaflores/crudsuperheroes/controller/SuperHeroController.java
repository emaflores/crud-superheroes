package com.emaflores.crudsuperheroes.controller;

import com.emaflores.crudsuperheroes.annotation.CustomTimer;
import com.emaflores.crudsuperheroes.exception.SuperHeroNotFoundException;
import com.emaflores.crudsuperheroes.model.SuperHero;
import com.emaflores.crudsuperheroes.service.SuperHeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/superheroes")
public class SuperHeroController {

    private final SuperHeroService service;

    @Autowired
    public SuperHeroController(SuperHeroService service) {
        this.service = service;
    }

    @CustomTimer
    @GetMapping
    public ResponseEntity<List<SuperHero>> getAllSuperHeroes() {
        List<SuperHero> superHeroes = service.getAllSuperHeroes();
        return ResponseEntity.ok(superHeroes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSuperHeroById(@PathVariable Long id) {
        try {
            SuperHero superHero = service.getSuperHeroById(id).orElseThrow(() ->
                    new SuperHeroNotFoundException("SuperHero with ID " + id + " not found"));
            return ResponseEntity.ok(superHero);
        } catch (Exception e) {
            return switch (e) {
                case IllegalArgumentException iae -> ResponseEntity.badRequest().body(iae.getMessage());
                case SuperHeroNotFoundException ignored -> ResponseEntity.notFound().build();
                default -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error");
            };
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<SuperHero>> searchSuperHeroesByName(@RequestParam String name) {
        List<SuperHero> superHeroes = service.searchSuperHeroesByName(name);
        return ResponseEntity.ok(superHeroes);
    }

    @PostMapping
    public ResponseEntity<SuperHero> createSuperHero(@Valid @RequestBody SuperHero superHero) {
        SuperHero createdSuperHero = service.createSuperHero(superHero);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSuperHero);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SuperHero> updateSuperHero(@PathVariable Long id, @Valid @RequestBody SuperHero superHero) {
        SuperHero updatedSuperHero = service.updateSuperHero(id, superHero);
        return ResponseEntity.ok(updatedSuperHero);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSuperHero(@PathVariable Long id) {
        service.deleteSuperHero(id);
        return ResponseEntity.ok().build();
    }
}
