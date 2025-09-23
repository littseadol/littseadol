package org.example.checker;

import java.io.IOException;

public class Main {
    private static final String HELP_MESSAGE ="java -jar target/main.jar src/main/resources/orig.txt src/main/resources/orig_0.8_add.txt src/main/resources/result.txt";


    public static void main(String[] args) {
        args = new String[]{"src/main/resources/orig.txt", "src/main/resources/orig_0.8_add.txt", "src/main/resources/result.txt"};
        // 参数验证
        if (args.length != 3) {
            System.err.println("❌ 参数错误: 需要3个参数");
            System.err.println(HELP_MESSAGE);
            System.exit(1);
        }

        String originalFilePath = args[0];
        String plagiarizedFilePath = args[1];
        String outputFilePath = args[2];

        System.out.println("🚀 启动论文查重系统");
        System.out.printf("🔍 参数配置:\n- 原文: %s\n- 抄袭版: %s\n- 输出: %s\n",
                originalFilePath, plagiarizedFilePath, outputFilePath);

        try {
            // 执行查重
            PlagiarismChecker checker = new PlagiarismChecker();
            double similarity = checker.check(originalFilePath, plagiarizedFilePath);

            // 结果输出
            FileUtils.writeResult(outputFilePath, similarity);
            System.out.printf("\n💾 结果已保存到: %s\n", outputFilePath);

            // 相似度分级提示
            printSimilarityLevel(similarity);

        } catch (IOException e) {
            System.err.println("\n❌ 处理过程中发生错误:");
            e.printStackTrace();
            System.err.println("\n💡 可能的解决方案:");
            System.err.println("1. 检查文件路径是否正确");
            System.err.println("2. 确认文件编码为UTF-8");
            System.err.println("3. 验证文件读取权限");
            System.exit(1);
        }
    }

    private static void printSimilarityLevel(double similarity) {
        System.out.println("\n📊 相似度分析报告:");
        double percent = similarity * 100;

        if (percent < 10) {
            System.out.printf("✅ 极低相似度 (%.2f%%): 文本高度原创\n", percent);
        } else if (percent < 20) {
            System.out.printf("⚠️ 低相似度 (%.2f%%): 可能存在少量引用\n", percent);
        } else if (percent < 30) {
            System.out.printf("❗ 中度相似 (%.2f%%): 需要检查是否适当引用\n", percent);
        } else if (percent < 50) {
            System.out.printf("❌ 高度相似 (%.2f%%): 可能存在抄袭\n", percent);
        } else {
            System.out.printf("🛑 极高相似度 (%.2f%%): 基本确认为抄袭\n", percent);
        }
    }
}