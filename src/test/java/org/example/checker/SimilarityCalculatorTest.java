package org.example.checker;


import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class SimilarityCalculatorTest {
    @Test
    void calculateCosineSimilarityIdenticalVectors() {
        SimilarityCalculator calculator = new SimilarityCalculator();
        Map<String, Integer> vector1 = new HashMap<>();
        vector1.put("hello", 2);
        vector1.put("world", 1);

        Map<String, Integer> vector2 = new HashMap<>();
        vector2.put("hello", 2);
        vector2.put("world", 1);

        double similarity = calculator.calculateCosineSimilarity(vector1, vector2);
        assertEquals(1.0, similarity, 0.01);
    }

    @Test
    void calculateCosineSimilarityDifferentVectors() {
        SimilarityCalculator calculator = new SimilarityCalculator();
        Map<String, Integer> vector1 = new HashMap<>();
        vector1.put("hello", 2);
        vector1.put("world", 1);

        Map<String, Integer> vector2 = new HashMap<>();
        vector2.put("goodbye", 2);
        vector2.put("moon", 1);

        double similarity = calculator.calculateCosineSimilarity(vector1, vector2);
        assertEquals(0.0, similarity, 0.01);
    }
}