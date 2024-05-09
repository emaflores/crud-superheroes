
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

import static org.junit.jupiter.api.Assertions.assertTrue;

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
        repository.deleteAll();
        SuperHero superHero = repository.save(new SuperHero(null, "Superman"));
        superHeroId = superHero.getId();
    }

    @Test
    public void testGetSuperHeroById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/superheroes/" + superHeroId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Superman"));
    }

    @Test
    public void testCreateSuperHero() throws Exception {
        String superHeroJson = "{\"name\":\"Batman\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/superheroes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(superHeroJson))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Batman"));
    }

    @Test
    public void testUpdateSuperHero() throws Exception {
        String superHeroJson = "{\"name\":\"Superman II\"}";
        mockMvc.perform(MockMvcRequestBuilders.put("/superheroes/" + superHeroId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(superHeroJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Superman II"));
    }

    @Test
    public void testDeleteSuperHero() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/superheroes/" + superHeroId))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


    @Test
    public void testGetAllSuperHeroes() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/superheroes")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Superman"));
    }

    @Test
    public void testGetSuperHeroById_NotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/superheroes/" + (superHeroId + 1))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testSearchSuperHeroesByName_Found() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/superheroes/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("name", "Superman"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Superman"));
    }

    @Test
    public void testSearchSuperHeroesByName_NotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/superheroes/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("name", "Batman"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isEmpty());
    }

    @Test
    public void testCreateSuperHero_InvalidData() throws Exception {
        String invalidSuperHeroJson = "{\"name\":\"\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/superheroes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidSuperHeroJson))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof IllegalArgumentException))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void testUpdateSuperHero_NotFound() throws Exception {
        String superHeroJson = "{\"name\":\"Superman II\"}";
        mockMvc.perform(MockMvcRequestBuilders.put("/superheroes/" + (superHeroId + 1))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(superHeroJson))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testDeleteSuperHero_NotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/superheroes/" + (superHeroId + 1)))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }


}