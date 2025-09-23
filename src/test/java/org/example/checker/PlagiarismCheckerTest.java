package org.example.checker;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class PlagiarismCheckerTest {

    @Test
    void checkIdenticalTexts() throws IOException {
        System.out.println("\n=== 测试相同文本 ===");

        // 初始化查重器
        PlagiarismChecker checker = new PlagiarismChecker();
        System.out.println("正在比较：orig.txt 和 orig.txt");

        // 计算相似度
        double similarity = checker.check(
                "src/test/resources/orig.txt",
                "src/test/resources/orig.txt"
        );

        // 打印详细结果
        System.out.printf("计算出的相似度：%.2f%% (预期：100.00%%)\n", similarity * 100);
        System.out.println("验证是否完全相似（误差±1%）...");

        // 断言
        assertEquals(1.0, similarity, 0.01);
        System.out.println("✅ 测试通过：相同文本相似度为100%");
    }

    @Test
    void checkDifferentTexts() throws IOException {
        System.out.println("\n=== 测试不同文本 ===");

        // 初始化查重器
        PlagiarismChecker checker = new PlagiarismChecker();
        System.out.println("正在比较：orig.txt 和 orig_0.8_add.txt");

        // 计算相似度
        double similarity = checker.check(
                "src/test/resources/orig.txt",
                "src/test/resources/orig_0.8_add.txt"
        );

        // 打印详细结果
        System.out.printf("计算出的相似度：%.2f%% (预期：<10.00%%)\n", similarity * 100);
        System.out.println("验证是否差异显著...");

        // 断言
        assertTrue(similarity < 0.1,
                String.format("错误：相似度 %.2f%% 高于预期的10%%,论文抄袭", similarity * 100));

        System.out.println("✅ 测试通过：不同文本相似度<10%");
    }
}