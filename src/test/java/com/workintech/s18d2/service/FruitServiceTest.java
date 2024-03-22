package com.workintech.s18d2.service;

import com.workintech.s18d2.entity.Fruit;
import com.workintech.s18d2.exceptions.PlantException;
import com.workintech.s18d2.repository.FruitRepository;
import com.workintech.s18d2.services.FruitServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FruitServiceTest {

    @Mock
    private FruitRepository fruitRepository;

    @InjectMocks
    private FruitServiceImpl fruitService;

    private Fruit fruit;

    @BeforeEach
    void setUp() {
        fruit = new Fruit();
        fruit.setId(1L);
        fruit.setName("Apple");
    }

    @Test
    void testGetByIdFound() {
        when(fruitRepository.findById(anyLong())).thenReturn(Optional.of(fruit));

        Fruit foundFruit = fruitService.getById(1L);

        assertNotNull(foundFruit);
        assertEquals(fruit.getName(), foundFruit.getName());
    }

    @Test
    void testGetByIdNotFound() {
        when(fruitRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(PlantException.class, () -> fruitService.getById(1L));
    }

    @Test
    void testGetByPriceAsc() {
        when(fruitRepository.getByPriceAsc()).thenReturn(Arrays.asList(fruit));

        List<Fruit> fruits = fruitService.getByPriceAsc();

        assertFalse(fruits.isEmpty());
        assertEquals(1, fruits.size());
    }

    @Test
    void testSave() {
        when(fruitRepository.save(any(Fruit.class))).thenReturn(fruit);

        Fruit savedFruit = fruitService.save(new Fruit());

        assertNotNull(savedFruit);
        assertEquals(fruit.getName(), savedFruit.getName());
    }

    @Test
    void testDelete() {
        when(fruitRepository.findById(anyLong())).thenReturn(Optional.of(fruit));
        doNothing().when(fruitRepository).delete(any(Fruit.class));

        Fruit deletedFruit = fruitService.delete(1L);

        assertNotNull(deletedFruit);
        assertEquals(fruit.getName(), deletedFruit.getName());
    }

    @Test
    void testSearchByName() {
        when(fruitRepository.searchByName(anyString())).thenReturn(Arrays.asList(fruit));

        List<Fruit> fruits = fruitService.searchByName("Apple");

        assertFalse(fruits.isEmpty());
        assertEquals(1, fruits.size());
        assertEquals(fruit.getName(), fruits.get(0).getName());
    }
}
