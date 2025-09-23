package org.example.checker;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class TextProcessor {
    private static final Pattern PUNCTUATION_PATTERN = Pattern.compile("[^\\p{L}\\p{N}]+");
    private static final Pattern WHITESPACE_PATTERN = Pattern.compile("\\s+");

    public Map<String, Integer> process(String text) {
        // 转换为小写并移除标点符号
        String cleanedText = PUNCTUATION_PATTERN.matcher(text.toLowerCase()).replaceAll(" ");

        // 分割为单词并过滤空字符串
        String[] words = WHITESPACE_PATTERN.split(cleanedText);

        // 统计词频
        return Arrays.stream(words)
                .filter(word -> !word.isEmpty())
                .collect(Collectors.toMap(
                        word -> word,
                        word -> 1,
                        Integer::sum,
                        HashMap::new
                ));
    }
}