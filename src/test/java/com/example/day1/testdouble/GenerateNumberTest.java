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
}