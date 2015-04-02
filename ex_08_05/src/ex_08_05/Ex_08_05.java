
package ex_08_05;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;


public class Ex_08_05 {


    public static void main(String[] args) throws IOException {
    
        String contents = new String(Files.readAllBytes(Paths.get("install.log")),StandardCharsets.UTF_8);
        List<String> words = Arrays.asList(contents.split("[\\P{L}]+")); 
        
        long startTime = System.nanoTime();
        countByStream(words);
        long endTime = System.nanoTime();
        System.out.println("countByStream spent \t"+ (endTime - startTime) + " nonoseconds.");//22303254000 nanoseconds

        startTime = System.nanoTime();
        countByForEach(words);
        endTime = System.nanoTime();
        System.out.println("countByForEach spent \t"+ (endTime - startTime) + " nonoseconds.");//13279936000 nanoseconds      
       
    }
    
    public static void countByStream(List<String> words){
        long count = words.stream().filter(w -> w.length() > 12).count();
        System.out.println(count);
    }
    
    public static void countByForEach(List<String> words){
        AtomicLong counter = new AtomicLong();
        words.forEach(word -> {
            if(word.length() > 12)
                counter.incrementAndGet();
        });
        System.out.println(counter.get());
    }
}
