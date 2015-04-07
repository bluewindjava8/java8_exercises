package ex_09_05;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Ex_09_05 {

    public static void main(String[] args) throws IOException {
        reverseChars("input.txt", "output.txt");
    }
    
    public static void reverseChars(String inputFile, String outputFile) throws IOException{
        byte[] bytes = Files.readAllBytes(Paths.get(inputFile));
        String content = new String(bytes, StandardCharsets.UTF_8);
        String reversedContent = new StringBuffer(content).reverse().toString();
        Files.write(Paths.get(outputFile), reversedContent.getBytes(StandardCharsets.UTF_8));
        
    }
}
