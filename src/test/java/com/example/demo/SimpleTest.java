package com.example.demo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SimpleTest {

    @Test
    void smokeTest() {
        // A simple test that always passes to demonstrate CI activity
        assertTrue(true, "The world is still turning");
    }
}
