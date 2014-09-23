
package ex_02_03;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class Ex_02_03 {

    public static void main(String[] args) throws IOException {
        long startTime = System.nanoTime();
        countByStream("install.log.big");
        long endTime = System.nanoTime();
        System.out.println("countByStream spent \t\t"+ (endTime - startTime) + " nonoseconds.");//22303254000 nanoseconds
        
        startTime = System.nanoTime();
        countByParallelStream("install.log.big");
        endTime = System.nanoTime();
        System.out.println("countByParallelStream spent \t"+ (endTime - startTime) + " nonoseconds.");//13279936000 nanoseconds      
        
    }
    
    public static void countByStream(String filename) throws IOException{
        String contents = new String(Files.readAllBytes(Paths.get(filename)),StandardCharsets.UTF_8);
        List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));
            
        long count = words.stream().filter(w -> w.length() > 12).count();
        System.out.println(count);
    }
    
    public static void countByParallelStream(String filename) throws IOException{
        String contents = new String(Files.readAllBytes(Paths.get(filename)),StandardCharsets.UTF_8);
        List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));
            
        long count = words.parallelStream().filter(w -> w.length() > 12).count();
        System.out.println(count); 
    }
}
