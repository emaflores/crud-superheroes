package com.emaflores.crudsuperheroes.repository;

import com.emaflores.crudsuperheroes.model.SuperHero;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SuperHeroRepository extends JpaRepository<SuperHero, Long> {

    Optional<SuperHero> findByNameIgnoreCase(String name);

    @Query("SELECT s FROM SuperHero s WHERE LOWER(s.name) LIKE CONCAT('%', LOWER(:name), '%')")
    List<SuperHero> findByNameContainingIgnoreCase(@Param("name") String name);
}


