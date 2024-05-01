package com.emaflores.crudsuperheroes.service.impl;

import com.emaflores.crudsuperheroes.model.SuperHero;
import com.emaflores.crudsuperheroes.repository.SuperHeroRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class SuperHeroServiceImplTest {

    @Mock
    private SuperHeroRepository repository;

    @InjectMocks
    private SuperHeroServiceImpl service;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllSuperHeroes() {
        List<SuperHero> superHeroes = new ArrayList<>();
        superHeroes.add(new SuperHero(1L, "Spiderman"));
        superHeroes.add(new SuperHero(2L, "Superman"));
        when(repository.findAll()).thenReturn(superHeroes);

        List<SuperHero> result = service.getAllSuperHeroes();

        assertEquals(2, result.size());
    }

    @Test
    public void testGetSuperHeroById() {
        SuperHero superHero = new SuperHero(1L, "Spiderman");
        when(repository.findById(1L)).thenReturn(Optional.of(superHero));

        SuperHero result = service.getSuperHeroById(1L);

        assertEquals("Spiderman", result.getName());
    }

    @Test
    public void testSearchSuperHeroesByName() {
        List<SuperHero> superHeroes = new ArrayList<>();
        superHeroes.add(new SuperHero(1L, "Spiderman"));
        superHeroes.add(new SuperHero(2L, "Superman"));
        when(repository.findByNameContaining("man")).thenReturn(superHeroes);

        List<SuperHero> result = service.searchSuperHeroesByName("man");

        assertEquals(2, result.size());
    }

    @Test
    public void testCreateSuperHero() {
        SuperHero superHero = new SuperHero(1L, "Spiderman");
        when(repository.save(superHero)).thenReturn(superHero);

        SuperHero result = service.createSuperHero(superHero);

        assertEquals("Spiderman", result.getName());
    }

    @Test
    public void testUpdateSuperHero() {
        SuperHero superHero = new SuperHero(1L, "Spiderman");
        when(repository.save(superHero)).thenReturn(superHero);

        SuperHero result = service.updateSuperHero(1L, superHero);

        assertEquals("Spiderman", result.getName());
    }

    @Test
    public void testDeleteSuperHero() {
        service.deleteSuperHero(1L);

        verify(repository, times(1)).deleteById(1L);
    }
}

