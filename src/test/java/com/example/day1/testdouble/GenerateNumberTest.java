package com.example.day1.testdouble;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class GenerateNumberTest {

    @Test
    @DisplayName("Get number result == 14 with Stub")
    void getNumber() {

        Random stubRandom = new Random() {
            @Override
            public int nextInt(int bound) {
                return 7;
            }
        };

        GenerateNumber g = new GenerateNumber(stubRandom);
        assertEquals(14, g.getNumber());
    }

    @Test
    @DisplayName("Check nextInt (int bound) called with Spy")
    void checkCall() {

        SpyRandom spyRandom = new SpyRandom();

        GenerateNumber g = new GenerateNumber(spyRandom);
        g.getNumber();
        g.getNumber();

        assertTrue(spyRandom.verify(2));
    }
}

class SpyRandom extends Random {
    int counter = 0;
    @Override
    public int nextInt(int bound) {
        counter++;
        return 7;
    }
    boolean verify(int expected) {
        return counter == expected;
    }
}