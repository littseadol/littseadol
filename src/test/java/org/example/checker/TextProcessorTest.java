package org.example.checker;

import org.junit.jupiter.api.Test;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class TextProcessorTest {
    @Test
    void processSimpleText() {
        TextProcessor processor = new TextProcessor();
        Map<String, Integer> result = processor.process("Hello world hello");

        assertEquals(2, result.size());
        assertEquals(2, result.get("hello"));
        assertEquals(1, result.get("world"));
    }

    @Test
    void processTextWithPunctuation() {
        TextProcessor processor = new TextProcessor();
        Map<String, Integer> result = processor.process("Hello, world! Hello?");

        assertEquals(2, result.size());
        assertEquals(2, result.get("hello"));
        assertEquals(1, result.get("world"));
    }
}