package com.workintech.s18d2.repository;

import com.workintech.s18d2.entity.Fruit;
import com.workintech.s18d2.entity.FruitType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@ActiveProfiles("test")
 class FruitRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private FruitRepository fruitRepository;

    @BeforeEach
     void setup() {
        // Setup data for each test
        Fruit apple = new Fruit();
        apple.setName("Apple");
        apple.setPrice(15.0);
        apple.setFruitType(FruitType.SWEET);
        entityManager.persist(apple);

        Fruit lemon = new Fruit();
        lemon.setName("Lemon");
        lemon.setPrice(25.0);
        lemon.setFruitType(FruitType.SOUR);
        entityManager.persist(lemon);

        entityManager.flush();
    }

    @Test
     void testGetByPriceDesc() {
        List<Fruit> fruits = fruitRepository.getByPriceDesc();
        assertEquals(2, fruits.size());
        assertTrue(fruits.get(0).getPrice() >= fruits.get(1).getPrice());
    }

    @Test
     void testGetByPriceAsc() {
        List<Fruit> fruits = fruitRepository.getByPriceAsc();
        assertEquals(2, fruits.size());
        assertTrue(fruits.get(0).getPrice() <= fruits.get(1).getPrice());
    }

    @Test
     void testSearchByName() {
        List<Fruit> fruits = fruitRepository.searchByName("Apple");
        assertEquals(1, fruits.size());
        assertEquals("Apple", fruits.get(0).getName());
    }
}
