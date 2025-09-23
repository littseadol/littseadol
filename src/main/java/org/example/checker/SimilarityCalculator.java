package org.example.checker;

import java.util.Map;
import java.util.Set;

public class SimilarityCalculator {
    public double calculateCosineSimilarity(Map<String, Integer> vector1, Map<String, Integer> vector2) {
        Set<String> commonWords = vector1.keySet();
        commonWords.retainAll(vector2.keySet());

        if (commonWords.isEmpty()) {
            return 0.0;
        }

        // 计算点积
        double dotProduct = commonWords.stream()
                .mapToDouble(word -> vector1.get(word) * vector2.get(word))
                .sum();

        // 计算向量的模
        double magnitude1 = Math.sqrt(vector1.values().stream()
                .mapToDouble(count -> Math.pow(count, 2))
                .sum());

        double magnitude2 = Math.sqrt(vector2.values().stream()
                .mapToDouble(count -> Math.pow(count, 2))
                .sum());

        if (magnitude1 == 0 || magnitude2 == 0) {
            return 0.0;
        }

        return dotProduct / (magnitude1 * magnitude2);
    }
}