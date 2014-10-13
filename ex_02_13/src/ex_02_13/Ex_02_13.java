
package ex_02_13;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Ex_02_13 {


    public static void main(String[] args)  throws IOException {
        Map<Integer, Long> shortWords = countByParallelStream("install.log.big", 12);
        System.out.println(shortWords);
        
    }
    
    public static Map<Integer, Long> countByParallelStream(String filename, int length) throws IOException{
        String contents = new String(Files.readAllBytes(Paths.get(filename)),StandardCharsets.UTF_8);
        List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));
           
        
        Map<Integer, Long> shortWords = words.parallelStream().filter(w -> w.length() < 12).collect(Collectors.groupingBy(w -> w.length(), Collectors.counting()));
        return shortWords;
    }
}

