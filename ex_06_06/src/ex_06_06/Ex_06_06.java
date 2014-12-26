package ex_06_06;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Ex_06_06 {

    public static void main(String[] args) {
        
    }
    
    public static Map<String, Set<File>> classifyWords(File[] files) throws InterruptedException{
        if(files == null){
            throw new NullPointerException("files is null.");
        }
        
        CountDownLatch latch = new CountDownLatch(files.length);
        Map<String, Set<File>> map = new ConcurrentHashMap<>();
        for(File file : files){
            new Thread( ()->{
                try {
                    String contents = new String(Files.readAllBytes(file.toPath()), StandardCharsets.UTF_8);
                    String[] words = contents.split("[\\P{L}]+");
                   
                    Set<File> fileSet = new HashSet<>();
                    fileSet.add(file);
                    for(String word : words){                                
                        map.computeIfAbsent(word, key -> fileSet);
                        map.compute(word, (key, existingSet) -> {
                                                Set<File> mergedSet = new HashSet<>(); 
                                                mergedSet.addAll(existingSet); 
                                                mergedSet.addAll(fileSet); 
                                                return mergedSet;
                                            }
                        );
                    }
                    
                    latch.countDown();
                } catch (IOException ex) {
                    Logger.getLogger(Ex_06_06.class.getName()).log(Level.SEVERE, null, ex);
                }
            }).start();
        }
        
        latch.await();
        return map;
    }
    
}
