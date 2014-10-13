
package ex_02_12;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Ex_02_12 {

    public static void main(String[] args)  throws IOException {
        AtomicInteger[] shortWords = countByParallelStream("install.log.big", 12);
        System.out.println(Arrays.asList(shortWords));
        
        int[] shortWordsSerial = countBySerialStream("install.log.big", 12);
        printIntArray(shortWordsSerial); 
        
        int[] shortWordsWrong = countByParallelStreamWrong("install.log.big", 12);
        printIntArray(shortWordsWrong);        
    }
    
    public static AtomicInteger[] countByParallelStream(String filename, int length) throws IOException{
        String contents = new String(Files.readAllBytes(Paths.get(filename)),StandardCharsets.UTF_8);
        List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));
            
        AtomicInteger[] shortWords = new AtomicInteger[length];
        for (int i = 0; i < length; i++){
            shortWords[i] = new AtomicInteger(0);
        }
        
        words.parallelStream().forEach(s -> { if (s.length() < 12) shortWords[s.length()].getAndIncrement(); });
        return shortWords;
    }
    
    
    public static int[] countBySerialStream(String filename, int length) throws IOException{
        String contents = new String(Files.readAllBytes(Paths.get(filename)),StandardCharsets.UTF_8);
        List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));
            
        int[] shortWords = new int[length];

        
        words.stream().forEach(s -> { if (s.length() < 12) shortWords[s.length()]++; });
        return shortWords;
    } 
    
    public static int[] countByParallelStreamWrong(String filename, int length) throws IOException{
        String contents = new String(Files.readAllBytes(Paths.get(filename)),StandardCharsets.UTF_8);
        List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));
            
        int[] shortWords = new int[length];

        
        words.parallelStream().forEach(s -> { if (s.length() < 12) shortWords[s.length()]++; });
        return shortWords;
    }   
     
     

    
    public static void printIntArray(int[] intArray){
        for(int i = 0; i < intArray.length; i++){
            System.out.println("intArray[ " + i +" ] = " + intArray[i]);
        }
        System.out.println();
    } 
}
