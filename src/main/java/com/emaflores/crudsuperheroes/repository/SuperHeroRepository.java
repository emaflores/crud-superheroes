package com.emaflores.crudsuperheroes.repository;

import com.emaflores.crudsuperheroes.model.SuperHero;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SuperHeroRepository extends JpaRepository<SuperHero, Long> {
    List<SuperHero> findByNameContaining(String name);
}
