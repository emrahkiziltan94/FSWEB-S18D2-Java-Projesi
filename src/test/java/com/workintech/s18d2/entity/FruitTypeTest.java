package com.workintech.s18d2.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

 class FruitTypeTest {

    @Test
     void enumShouldContainExpectedValues() {
        assertTrue(FruitType.valueOf("SWEET") == FruitType.SWEET);
        assertTrue(FruitType.valueOf("SOUR") == FruitType.SOUR);
    }

    @Test
     void enumShouldContainExactNumberOfValues() {
        assertTrue(FruitType.values().length == 2);
    }
}
