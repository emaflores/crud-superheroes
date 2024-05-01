package com.emaflores.crudsuperheroes.controller;

import com.emaflores.crudsuperheroes.model.SuperHero;
import com.emaflores.crudsuperheroes.repository.SuperHeroRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class SuperHeroIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SuperHeroRepository repository;

    private Long superHeroId;

    @BeforeEach
    public void setup() {
        SuperHero superHero = repository.save(new SuperHero(1L, "Superman"));
    }

    @Test
    public void testGetSuperHeroById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/superheroes/1" )
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Superman"));
    }
}

