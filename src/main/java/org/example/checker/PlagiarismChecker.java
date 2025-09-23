package org.example.checker;

import java.io.IOException;
import java.util.Map;

public class PlagiarismChecker {
    private static final String DIVIDER = "========================================";
    private final TextProcessor textProcessor;
    private final SimilarityCalculator similarityCalculator;

    public PlagiarismChecker() {
        this.textProcessor = new TextProcessor();
        this.similarityCalculator = new SimilarityCalculator();
    }

    public double check(String originalFilePath, String plagiarizedFilePath) throws IOException {
        System.out.println("\n" + DIVIDER);
        System.out.println("🛠️ 开始论文查重分析");
        System.out.println(DIVIDER);

        // 1. 文件读取阶段
        System.out.printf("\n📂 正在读取文件:\n- 原文: %s\n- 抄袭版: %s\n",
                originalFilePath, plagiarizedFilePath);

        long startTime = System.currentTimeMillis();
        String originalText = FileUtils.readFile(originalFilePath);
        String plagiarizedText = FileUtils.readFile(plagiarizedFilePath);

        System.out.printf("✅ 文件读取完成 (耗时: %dms)\n",
                System.currentTimeMillis() - startTime);

        // 2. 文本预处理阶段
        System.out.println("\n🔧 正在预处理文本...");
        startTime = System.currentTimeMillis();

        Map<String, Integer> originalVector = textProcessor.process(originalText);
        Map<String, Integer> plagiarizedVector = textProcessor.process(plagiarizedText);

        System.out.printf("✅ 文本预处理完成 (耗时: %dms)\n",
                System.currentTimeMillis() - startTime);
        System.out.println("📊 词频统计摘要:");
        System.out.printf("- 原文特征词数量: %d (示例: %s)\n",
                originalVector.size(), getSampleKeys(originalVector));
        System.out.printf("- 抄袭版特征词数量: %d (示例: %s)\n",
                plagiarizedVector.size(), getSampleKeys(plagiarizedVector));

        // 3. 相似度计算阶段
        System.out.println("\n🧮 正在计算余弦相似度...");
        startTime = System.currentTimeMillis();

        double similarity = similarityCalculator.calculateCosineSimilarity(
                originalVector,
                plagiarizedVector
        );

        System.out.printf("✅ 计算完成 (耗时: %dms)\n",
                System.currentTimeMillis() - startTime);
        System.out.println(DIVIDER);
        System.out.printf("\n📈 最终相似度: %.2f%%\n", similarity * 100);
        System.out.println(DIVIDER);

        return similarity;
    }

    private String getSampleKeys(Map<String, Integer> map) {
        return map.keySet().stream()
                .limit(5)
                .reduce((a, b) -> a + ", " + b)
                .orElse("无数据");
    }
}
