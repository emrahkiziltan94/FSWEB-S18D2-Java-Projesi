package com.workintech.s18d2.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.workintech.s18d2.entity.Fruit;
import com.workintech.s18d2.entity.FruitType;
import com.workintech.s18d2.exceptions.PlantException;
import com.workintech.s18d2.services.FruitService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(FruitController.class)
class FruitControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private FruitService fruitService;

    private Fruit sampleFruit;

    @BeforeEach
    void setUp() {
        sampleFruit = new Fruit();
        sampleFruit.setId(1L);
        sampleFruit.setName("Apple");
        sampleFruit.setPrice(20.0);
        sampleFruit.setFruitType(FruitType.SWEET);
    }

    @Test
    void testGetFruits() throws Exception {
        given(fruitService.getByPriceAsc()).willReturn(List.of(sampleFruit));
        mockMvc.perform(get("/fruit"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is("Apple")));
    }

    @Test
    void testGetFruitsDesc() throws Exception {
        given(fruitService.getByPriceDesc()).willReturn(List.of(sampleFruit));
        mockMvc.perform(get("/fruit/desc"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is("Apple")));
    }

    @Test
    void testGetFruitsByName() throws Exception {
        given(fruitService.searchByName("Apple")).willReturn(List.of(sampleFruit));
        mockMvc.perform(get("/fruit/name/{name}", "Apple"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is("Apple")));
    }

    @Test
    void testSaveFruit() throws Exception {
        given(fruitService.save(any())).willReturn(sampleFruit);
        mockMvc.perform(post("/fruit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(sampleFruit)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Apple")));
    }

    @Test
    void testGetByIdSuccess() throws Exception {
        given(fruitService.getById(1L)).willReturn(sampleFruit);
        mockMvc.perform(get("/fruit/{id}", 1L))
                .andExpect(status().isOk());
    }



    @Test
    void testDeleteFruitSuccess() throws Exception {
        when(fruitService.delete(anyLong())).thenReturn(sampleFruit);
        mockMvc.perform(delete("/fruit/{id}", 1L))
                .andExpect(status().isOk());
    }


}
