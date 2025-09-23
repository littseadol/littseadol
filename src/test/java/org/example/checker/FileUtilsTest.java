package org.example.checker;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import static org.junit.jupiter.api.Assertions.*;

class FileUtilsTest {
    @Test
    void readFile() throws IOException {
        String content = FileUtils.readFile("src/test/resources/test.txt");
        assertEquals("test content", content.trim());
    }

    @Test
    void writeResult() throws IOException {
        String outputPath = "src/test/resources/output.txt";
        FileUtils.writeResult(outputPath, 0.85);

        String content = new String(Files.readAllBytes(Paths.get(outputPath)));
        assertEquals("85.00", content.trim());

        // Clean up
        Files.deleteIfExists(Paths.get(outputPath));
    }
}