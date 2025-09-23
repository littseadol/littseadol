package org.example.checker;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;

public class FileUtils {
    public static String readFile(String filePath) throws IOException {
        return new String(Files.readAllBytes(Paths.get(filePath)), StandardCharsets.UTF_8);
    }

    public static void writeResult(String outputFilePath, double similarity) throws IOException {
        DecimalFormat df = new DecimalFormat("0.00");
        String result = df.format(similarity * 100);
        Files.write(Paths.get(outputFilePath), result.getBytes(StandardCharsets.UTF_8));
    }
}