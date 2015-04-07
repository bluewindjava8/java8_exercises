package ex_09_06;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

public class Ex_09_06 {

    public static void main(String[] args) throws IOException {
        reverseLines("input.txt", "output.txt");
    }
    
    public static void reverseLines(String inputFile, String outputFile) throws IOException{
        List<String> lines = Files.readAllLines(Paths.get(inputFile));
        Collections.reverse(lines);
        Files.write(Paths.get(outputFile), lines, StandardCharsets.UTF_8);
        
    }
}
