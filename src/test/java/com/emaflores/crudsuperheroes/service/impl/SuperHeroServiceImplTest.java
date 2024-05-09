package com.emaflores.crudsuperheroes.service.impl;

import com.emaflores.crudsuperheroes.exception.SuperHeroNotFoundException;
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

import static org.junit.jupiter.api.Assertions.*;
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
        verify(repository).findAll();
    }

    @Test
    public void testGetSuperHeroById() {
        SuperHero superHero = new SuperHero(1L, "Spiderman");
        when(repository.findById(1L)).thenReturn(Optional.of(superHero));

        Optional<SuperHero> result = service.getSuperHeroById(1L);

        assertTrue(result.isPresent());
        assertEquals("Spiderman", result.get().getName());
    }

    @Test
    public void testGetSuperHeroById_NotFound() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        SuperHeroNotFoundException thrown = assertThrows(
                SuperHeroNotFoundException.class,
                () -> service.getSuperHeroById(1L).orElseThrow(
                        () -> new SuperHeroNotFoundException("SuperHero with ID " + 1L + " not found")
                ),
                "Expected getSuperHeroById(1L) to throw, but it didn't"
        );

        assertTrue(thrown.getMessage().contains("SuperHero with ID 1 not found"));
    }


    @Test
    public void testSearchSuperHeroesByName() {
        List<SuperHero> superHeroes = new ArrayList<>();
        superHeroes.add(new SuperHero(1L, "Spiderman"));
        superHeroes.add(new SuperHero(2L, "Superman"));
        when(repository.findByNameContainingIgnoreCase("man")).thenReturn(superHeroes);

        List<SuperHero> result = service.searchSuperHeroesByName("man");

        assertEquals(2, result.size());
        verify(repository).findByNameContainingIgnoreCase("man");
    }

    @Test
    public void testCreateSuperHero() {
        SuperHero superHero = new SuperHero(null, "Batman");
        when(repository.findByNameIgnoreCase("Batman")).thenReturn(Optional.empty());
        when(repository.save(superHero)).thenReturn(new SuperHero(1L, "Batman"));

        SuperHero result = service.createSuperHero(superHero);

        assertEquals("Batman", result.getName());
    }

    @Test
    public void testCreateSuperHero_AlreadyExists() {
        SuperHero superHero = new SuperHero(null, "Batman");
        when(repository.findByNameIgnoreCase("Batman")).thenReturn(Optional.of(new SuperHero(1L, "Batman")));

        Exception exception = assertThrows(IllegalStateException.class, () -> {
            service.createSuperHero(superHero);
        });

        assertEquals("SuperHero already exists with this name", exception.getMessage());
    }

    @Test
    public void testUpdateSuperHero() {
        SuperHero superHero = new SuperHero(1L, "Batman");
        when(repository.existsById(1L)).thenReturn(true);
        when(repository.save(superHero)).thenReturn(superHero);

        SuperHero result = service.updateSuperHero(1L, superHero);

        assertEquals("Batman", result.getName());
        verify(repository).save(superHero);
    }

    @Test
    public void testDeleteSuperHero() {
        when(repository.existsById(1L)).thenReturn(true);
        doNothing().when(repository).deleteById(1L);

        service.deleteSuperHero(1L);

        verify(repository).deleteById(1L);
    }

    @Test
    public void testDeleteSuperHero_InvalidId() {
        long invalidId = -1L;

        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> service.deleteSuperHero(invalidId),
                "Expected deleteSuperHero() to throw IllegalArgumentException, but it didn't"
        );

        assertEquals("Invalid ID: ID must be greater than zero.", thrown.getMessage());
    }


    @Test
    public void testDeleteSuperHero_NotFound() {
        when(repository.existsById(1L)).thenReturn(false);

        Exception exception = assertThrows(IllegalStateException.class, () -> {
            service.deleteSuperHero(1L);
        });

        assertEquals("SuperHero with ID " + 1L + " does not exist", exception.getMessage());
    }
}