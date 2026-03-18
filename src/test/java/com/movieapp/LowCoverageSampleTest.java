package com.movieapp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LowCoverageSampleTest {
    @Test
    void testAdd() {
        LowCoverageSample sample = new LowCoverageSample();
        assertEquals(5, sample.add(2, 3));
    }
    // ...no tests for other methods...
}
