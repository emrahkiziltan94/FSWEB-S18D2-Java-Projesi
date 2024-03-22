package com.workintech.s18d2.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class VegetableTest {

    @Test
    void testVegetableProperties() {
        Vegetable vegetable = new Vegetable();
        vegetable.setId(2L);
        vegetable.setName("Carrot");
        vegetable.setPrice(20.0);
        vegetable.setGrownOnTree(false);

        assertEquals(2L, vegetable.getId());
        assertEquals("Carrot", vegetable.getName());
        assertEquals(20.0, vegetable.getPrice());
        assertFalse(vegetable.isGrownOnTree());

        // Test setting isGrownOnTree to true
        vegetable.setGrownOnTree(true);
        assertTrue(vegetable.isGrownOnTree());
    }
}
