package com.workintech.s18d2.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

 class FruitTest {

    @Test
     void testFruitProperties() {
        Fruit fruit = new Fruit();
        fruit.setId(1L);
        fruit.setName("Apple");
        fruit.setPrice(15.0);
        fruit.setFruitType(FruitType.SWEET);

        assertEquals(1L, fruit.getId());
        assertEquals("Apple", fruit.getName());
        assertEquals(15.0, fruit.getPrice());
        assertEquals(FruitType.SWEET, fruit.getFruitType());
    }
}
